package tech.alexnijjar.endermanoverhaul.common.entities.pets;

import com.geckolib.animatable.GeoEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.object.PlayState;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantAnimations;
import tech.alexnijjar.endermanoverhaul.common.entities.base.BaseEnderman;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;
import tech.alexnijjar.endermanoverhaul.common.registry.ModParticleTypes;

import java.util.*;

public abstract class BasePetEnderman extends BaseEnderman implements GeoEntity, OwnableEntity {
    protected static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(BasePetEnderman.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private EndermanHurtByTargetGoal hurtByTargetGoal;

    public BasePetEnderman(EntityType<? extends BaseEnderman> entityType, Level level) {
        super(entityType, level);
    }

    public BasePetEnderman(EntityType<? extends BaseEnderman> entityType, Level level, Player owner) {
        super(entityType, level);
        this.setOwnerUUID(owner.getUUID());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_OWNERUUID_ID, Optional.empty());
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("main", 5, state -> {
            if (state.isMoving()) {
                state.setAnimation(isCreepy() ?
                    ConstantAnimations.RUN :
                    ConstantAnimations.WALK);
            } else {
                state.setAnimation(ConstantAnimations.IDLE);
            }
            return PlayState.CONTINUE;
        }));

        controllers.add(new AnimationController<>("attack_controller", 5, state -> {
            if (!playArmSwingAnimWhenAttacking()) return PlayState.STOP;
            if (getAttackAnim(state.renderState().getPartialTick()) == 0) return PlayState.STOP;
            state.setAnimation(ConstantAnimations.ATTACK);
            return PlayState.CONTINUE;
        }));
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.addGoal(6, new EndermanFollowOwnerGoal(1.0, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new EndermanOwnerHurtByTargetGoal());
        this.targetSelector.addGoal(2, new EndermanOwnerHurtTargetGoal());
        this.targetSelector.addGoal(3, hurtByTargetGoal = new EndermanHurtByTargetGoal());
    }

    @Override
    public void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        EntityReference.store(getOwnerReference(), output, "Owner");
    }

    @Override
    public void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(
            EntityReference.readWithOldOwnerConversion(input, "Owner", level())));
    }

    @Override
    public @Nullable EntityReference<LivingEntity> getOwnerReference() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }

    public @Nullable UUID getOwnerUUID() {
        var owner = getOwnerReference();
        return owner == null ? null : owner.getUUID();
    }

    public void setOwnerUUID(@Nullable UUID uuid) {
        this.entityData.set(DATA_OWNERUUID_ID, uuid == null ? Optional.empty() : Optional.of(EntityReference.of(uuid)));
    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide() && getTarget() instanceof BasePetEnderman e && Objects.equals(e.getOwnerUUID(), getOwnerUUID())) {
            setTarget(null);
            setLastHurtByMob(null);
        }

        if (!level().isClientSide() && getTarget() instanceof Player p && p.getUUID().equals(getOwnerUUID())) {
            setTarget(null);
            setLastHurtByMob(null);
        }
    }

    @Override
    public @Nullable ParticleOptions getCustomParticles() {
        return ModParticleTypes.FRIENDERMAN.get();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide() && this.isAlive() && this.getTickCount() % 2000 == 0) {
            this.heal(1.0f);
        }
    }

    public boolean wantsToAttack(LivingEntity target) {
        if (target instanceof Creeper) return false;
        if (target instanceof Ghast) return false;
        if (target instanceof BasePetEnderman otherPet) {
            return !Objects.equals(otherPet.getOwnerUUID(), getOwnerUUID());
        }
        if (target instanceof Player player) {
            return !player.getUUID().equals(getOwnerUUID());
        }
        return true;
    }

    @Override
    public void die(@NotNull DamageSource damageSource) {
        if (this.level() instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(GameRules.SHOW_DEATH_MESSAGES) && this.getOwner() instanceof ServerPlayer owner) {
            owner.sendSystemMessage(this.getCombatTracker().getDeathMessage());
        }

        super.die(damageSource);
    }

    @Override
    public boolean isProvokedByEyeContact() {
        return false;
    }

    @Override
    public boolean canOpenMouth() {
        return false;
    }

    public class EndermanOwnerHurtByTargetGoal extends TargetGoal {
        private LivingEntity ownerLastHurtBy;
        private int timestamp;

        public EndermanOwnerHurtByTargetGoal() {
            super(BasePetEnderman.this, false);
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        public boolean canUse() {
            LivingEntity livingEntity = getOwner();
            if (livingEntity == null) {
                return false;
            } else {
                this.ownerLastHurtBy = livingEntity.getLastHurtByMob();
                int i = livingEntity.getLastHurtByMobTimestamp();
                return i != this.timestamp && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT) && wantsToAttack(this.ownerLastHurtBy);
            }
        }

        public void start() {
            this.mob.setTarget(this.ownerLastHurtBy);
            LivingEntity livingEntity = getOwner();
            if (livingEntity != null) {
                this.timestamp = livingEntity.getLastHurtByMobTimestamp();
            }

            super.start();
        }
    }

    public class EndermanOwnerHurtTargetGoal extends TargetGoal {
        private LivingEntity ownerLastHurt;
        private int timestamp;

        public EndermanOwnerHurtTargetGoal() {
            super(BasePetEnderman.this, false);
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        public boolean canUse() {
            LivingEntity livingEntity = getOwner();
            if (livingEntity == null) {
                return false;
            } else {
                this.ownerLastHurt = livingEntity.getLastHurtMob();
                int i = livingEntity.getLastHurtMobTimestamp();
                return i != this.timestamp && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT) && wantsToAttack(this.ownerLastHurt);
            }
        }

        public void start() {
            this.mob.setTarget(this.ownerLastHurt);
            LivingEntity livingEntity = getOwner();
            if (livingEntity != null) {
                this.timestamp = livingEntity.getLastHurtMobTimestamp();
            }

            super.start();
        }
    }

    @Override
    protected @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!player.getUUID().equals(getOwnerUUID())) return InteractionResult.PASS;
        if (!level().isClientSide()) {
            ItemStack stack = ModItems.ANCIENT_PEARL.get().getDefaultInstance();
            var output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registryAccess());
            saveWithoutId(output);
            stack.set(DataComponents.ENTITY_DATA, TypedEntityData.of(getType(), output.buildResult()));
            BehaviorUtils.throwItem(this, stack, position());
            playSound(SoundEvents.ENDERMAN_TELEPORT);
            this.discard();
            return InteractionResult.SUCCESS;
        }

        for (int i = 0; i < getParticleCount(); i++) {
            this.level().addParticle(ParticleTypes.PORTAL,
                this.getRandomX(0.5),
                this.getRandomY() - 0.25,
                this.getRandomZ(0.5),
                (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(),
                (this.random.nextDouble() - 0.5) * 2.0);
        }
        return InteractionResult.SUCCESS;
    }

    public class EndermanFollowOwnerGoal extends Goal {
        private LivingEntity owner;
        private final LevelReader level;
        private final double speedModifier;
        private final PathNavigation navigation;
        private int timeToRecalcPath;
        private final float stopDistance;
        private final float startDistance;
        private float oldWaterCost;
        private final boolean canFly;

        public EndermanFollowOwnerGoal(double d, float f, float g, boolean bl) {
            this.level = level();
            this.speedModifier = d;
            this.navigation = getNavigation();
            this.startDistance = f;
            this.stopDistance = g;
            this.canFly = bl;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
            if (!(getNavigation() instanceof GroundPathNavigation) && !(getNavigation() instanceof FlyingPathNavigation)) {
                throw new IllegalArgumentException("Unsupported mob type for FollowOwnerGoal");
            }
        }

        public boolean canUse() {
            LivingEntity livingEntity = getOwner();
            if (livingEntity == null) {
                return false;
            } else if (livingEntity.isSpectator()) {
                return false;
            } else if (this.unableToMove()) {
                return false;
            } else if (distanceToSqr(livingEntity) < (double) (this.startDistance * this.startDistance)) {
                return false;
            } else {
                this.owner = livingEntity;
                return true;
            }
        }

        public boolean canContinueToUse() {
            if (this.navigation.isDone()) {
                return false;
            } else if (this.unableToMove()) {
                return false;
            } else {
                return !(distanceToSqr(this.owner) <= (double) (this.stopDistance * this.stopDistance));
            }
        }

        private boolean unableToMove() {
            return isPassenger() || isLeashed();
        }

        public void start() {
            this.timeToRecalcPath = 0;
            this.oldWaterCost = getPathfindingMalus(PathType.WATER);
            setPathfindingMalus(PathType.WATER, 0.0F);
        }

        public void stop() {
            this.owner = null;
            this.navigation.stop();
            setPathfindingMalus(PathType.WATER, this.oldWaterCost);
        }

        public void tick() {
            getLookControl().setLookAt(this.owner, 10.0F, (float) getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                if (distanceToSqr(this.owner) >= Math.pow(20, 2)) {
                    this.teleportToOwner();
                } else {
                    this.navigation.moveTo(this.owner, this.speedModifier);
                }
            }
        }

        private void teleportToOwner() {
            BlockPos blockPos = this.owner.blockPosition();

            for (int i = 0; i < 10; i++) {
                int j = this.randomIntInclusive(-3, 3);
                int k = this.randomIntInclusive(-1, 1);
                int l = this.randomIntInclusive(-3, 3);
                boolean teleported = this.maybeTeleportTo(blockPos.getX() + j, blockPos.getY() + k, blockPos.getZ() + l);
                if (teleported) return;
            }

        }

        private boolean maybeTeleportTo(int x, int y, int z) {
            if (Math.abs((double) x - this.owner.getX()) < 2.0 && Math.abs((double) z - this.owner.getZ()) < 2.0) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(x, y, z))) {
                return false;
            } else {
                snapTo((double) x + 0.5, y, (double) z + 0.5, getYRot(), getXRot());
                this.navigation.stop();
                return true;
            }
        }

        private boolean canTeleportTo(BlockPos pos) {
            PathType blockPathTypes = WalkNodeEvaluator.getPathTypeStatic(BasePetEnderman.this, pos.mutable());
            if (blockPathTypes != PathType.WALKABLE) {
                return false;
            } else {
                BlockState blockState = this.level.getBlockState(pos.below());
                if (!this.canFly && blockState.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos blockPos = pos.subtract(blockPosition());
                    return this.level.noCollision(BasePetEnderman.this, getBoundingBox().move(blockPos));
                }
            }
        }

        private int randomIntInclusive(int min, int max) {
            return getRandom().nextInt(max - min + 1) + min;
        }
    }

    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public class EndermanHurtByTargetGoal extends TargetGoal {
        private static final TargetingConditions HURT_BY_TARGETING = TargetingConditions.forCombat().ignoreLineOfSight().ignoreInvisibilityTesting();
        private int timestamp;
        private final Class<?>[] toIgnoreDamage;

        public EndermanHurtByTargetGoal(Class<?>... classes) {
            super(BasePetEnderman.this, true);
            this.toIgnoreDamage = classes;
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        public boolean canUse() {
            int i = getLastHurtByMobTimestamp();
            LivingEntity livingEntity = getLastHurtByMob();
            if (i != this.timestamp && livingEntity != null) {
                if (livingEntity.getType() == EntityType.PLAYER && ((ServerLevel) level()).getGameRules().get(GameRules.UNIVERSAL_ANGER)) {
                    return false;
                } else {
                    if (!wantsToAttack(livingEntity)) return false;

                    for (Class<?> clazz : this.toIgnoreDamage) {
                        if (clazz.isAssignableFrom(livingEntity.getClass())) {
                            return false;
                        }
                    }

                    return this.canAttack(livingEntity, HURT_BY_TARGETING);
                }
            } else {
                return false;
            }
        }

        public void start() {
            setTarget(getLastHurtByMob());
            this.targetMob = getTarget();
            this.timestamp = getLastHurtByMobTimestamp();
            this.unseenMemoryTicks = 300;
            alertOthers();
            super.start();
        }

        public void notifyStart() {
            setTarget(getLastHurtByMob());
            this.targetMob = getTarget();
            this.timestamp = getLastHurtByMobTimestamp();
            this.unseenMemoryTicks = 300;
            super.start();
        }

        public void alertOthers() {
            UUID owner = getOwnerUUID();
            if (owner == null) return;
            LivingEntity targetMob = getTarget();
            if (targetMob instanceof Player p && p.getUUID().equals(owner)) return;
            if (targetMob == null) return;

            AABB aabb = AABB.unitCubeFromLowerCorner(position()).inflate(30f);
            List<BasePetEnderman> pets = level().getEntitiesOfClass(BasePetEnderman.class, aabb, EntitySelector.NO_SPECTATORS);
            pets.remove(BasePetEnderman.this);

            List<BasePetEnderman> ownedPets = pets.stream().filter(pet -> owner.equals(pet.getOwnerUUID())).toList();
            if (ownedPets.size() <= 1) return;

            List<LivingEntity> targets = new ArrayList<>();
            if (targetMob instanceof BasePetEnderman target) {
                UUID targetOwner = target.getOwnerUUID();
                if (targetOwner == null) return;
                targets.addAll(pets.stream().filter(pet -> targetOwner.equals(pet.getOwnerUUID())).toList());
                Player player = level().getPlayerByUUID(targetOwner);
                if (player != null && player.distanceTo(BasePetEnderman.this) < 30.0f) {
                    targets.add(player);
                }
                if (targets.isEmpty()) return;
            } else if (targetMob instanceof Player player) {
                targets.add(player);
                targets.addAll(pets.stream().filter(pet -> player.getUUID().equals(pet.getOwnerUUID())).toList());
            } else {
                AABB aabb2 = AABB.unitCubeFromLowerCorner(position()).inflate(20f);
                targets.addAll(level().getEntitiesOfClass(targetMob.getClass(), aabb2, EntitySelector.NO_SPECTATORS));
            }

            for (var pet : ownedPets) {
                LivingEntity closestTarget = targets.stream().min(Comparator.comparingDouble(pet::distanceToSqr)).orElse(null);
                pet.setLastHurtByMob(closestTarget);
                pet.hurtByTargetGoal.notifyStart();
            }
        }
    }
}

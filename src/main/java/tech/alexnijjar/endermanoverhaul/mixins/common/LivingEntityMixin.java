package tech.alexnijjar.endermanoverhaul.mixins.common;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;
import tech.alexnijjar.endermanoverhaul.common.utils.ModUtils;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    protected ItemStack useItem;

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(
        method = "blockUsingItem",
        at = @At("TAIL")
    )
    private void endermanoverhaul$blockUsingItem(ServerLevel level, LivingEntity attacker, DamageSource source, float blockedDamage, boolean parried, CallbackInfo ci) {
        if (!useItem.is(ModItems.CORRUPTED_SHIELD.get())) return;
        if (attacker.typeHolder().is(ConventionalEntityTypeTags.TELEPORTING_NOT_SUPPORTED)) return;

        if (attacker.level().getRandom().nextInt(4) != 0) {
            ModUtils.teleportTarget(attacker.level(), attacker, 32);
            attacker.hurtServer(level, attacker.damageSources().fall(), 2.0f);
        }
    }
}

package tech.alexnijjar.endermanoverhaul.common.items.pearls;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantComponents;
import tech.alexnijjar.endermanoverhaul.common.entities.pets.BasePetEnderman;
import tech.alexnijjar.endermanoverhaul.common.entities.projectiles.ThrownAncientPearl;

import java.util.function.Consumer;

public class AncientPearlItem extends EnderpearlItem {
    public static final int HEAL_TICK_RATE = 2500;

    public AncientPearlItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f));
        player.getCooldowns().addCooldown(stack, 20);
        if (!level.isClientSide()) {
            ThrownAncientPearl pearl;

            var data = stack.get(DataComponents.ENTITY_DATA);
            if (data != null) {
                Entity entity = data.type().create(level, EntitySpawnReason.TRIGGERED);
                if (!(entity instanceof BasePetEnderman pet)) return InteractionResult.FAIL;
                data.loadInto(pet);
                pet.setOwnerUUID(player.getUUID());
                pearl = new ThrownAncientPearl(level, player, pet);
            } else {
                pearl = new ThrownAncientPearl(level, player);
            }

            pearl.setItem(stack);
            pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 1.0f);
            level.addFreshEntity(pearl);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.accept(ConstantComponents.ANCIENT_PEARL_TOOLTIP_1);
        tooltipComponents.accept(ConstantComponents.ANCIENT_PEARL_TOOLTIP_2);

        var data = stack.get(DataComponents.ENTITY_DATA);
        if (data != null) {
            int health = (int) data.copyTagWithoutId().getFloatOr("Health", 30);
            tooltipComponents.accept(Component.translatable("tooltip.endermanoverhaul.ancient_pet", health).withStyle(ChatFormatting.GREEN));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        if ((entity.tickCount + entity.getId()) % HEAL_TICK_RATE != 0) return;

        var data = stack.get(DataComponents.ENTITY_DATA);
        if (data != null) {
            CompoundTag tag = data.copyTagWithoutId();
            float health = tag.getFloatOr("Health", 30);
            if (health < 30) {
                tag.putFloat("Health", Math.min(health + 1, 30));
                stack.set(DataComponents.ENTITY_DATA, TypedEntityData.of(data.type(), tag));
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return stack.has(DataComponents.ENTITY_DATA) || super.isFoil(stack);
    }
}

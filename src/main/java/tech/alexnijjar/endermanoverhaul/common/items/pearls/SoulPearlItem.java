package tech.alexnijjar.endermanoverhaul.common.items.pearls;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Prediction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.client.EndermanOverhaulClient;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantComponents;
import tech.alexnijjar.endermanoverhaul.common.entities.projectiles.ThrownSoulPearl;
import tech.alexnijjar.endermanoverhaul.common.registry.ModDataComponents;

import java.util.function.Consumer;

public class SoulPearlItem extends EnderpearlItem {
    public SoulPearlItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        if (player.isShiftKeyDown()) return InteractionResult.PASS;
        ItemStack itemStack = player.getItemInHand(usedHand);
        int id = getBoundEntityId(itemStack);
        Entity entity = level.getEntity(id);
        if (entity == null) return InteractionResult.FAIL;
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f));
        player.getCooldowns().addCooldown(itemStack, 20);
        if (!level.isClientSide()) {
            ThrownSoulPearl pearl = new ThrownSoulPearl(level, player, entity);
            pearl.setItem(itemStack);
            pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 1.5f, 1.0f);
            level.addFreshEntity(pearl);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity interactionTarget, @NotNull InteractionHand usedHand) {
        if (!player.level().isClientSide() && player.isShiftKeyDown() && !interactionTarget.typeHolder().is(ConventionalEntityTypeTags.TELEPORTING_NOT_SUPPORTED)) {
            if (interactionTarget.getId() == getBoundEntityId(stack)) {
                return InteractionResult.PASS;
            }

            ItemStack copy = stack.copy();
            copy.setCount(1);
            setBoundEntityId(copy, interactionTarget.getId());
            Component displayName = interactionTarget.getDisplayName();
            if (displayName == null) return InteractionResult.FAIL;
            player.sendOverlayMessage(Component.translatable("tooltip.endermanoverhaul.bound_to", displayName));
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.EVOKER_CAST_SPELL, SoundSource.NEUTRAL, 1.0f, 1.0f);
            if (stack.getCount() == 1) {
                player.setItemInHand(usedHand, copy);
            } else {
                stack.shrink(1);
                if (!player.getInventory().add(copy)) {
                    player.drop(copy, false, Prediction.SERVER_ONLY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.interactLivingEntity(stack, player, interactionTarget, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.accept(ConstantComponents.SOUL_PEARL_TOOLTIP_1);
        tooltipComponents.accept(ConstantComponents.SOUL_PEARL_TOOLTIP_2);

        if (isBound(stack)) {
            Level level = EndermanOverhaulClient.getLevel();
            if (level == null) return;
            int id = getBoundEntityId(stack);
            Entity entity = level.getEntity(id);
            if (entity == null) return;
            Component displayName = entity.getDisplayName();
            if (displayName == null) return;
            tooltipComponents.accept(Component.translatable("tooltip.endermanoverhaul.bound_to", displayName.getString()).withStyle(ChatFormatting.GREEN));
        } else {
            tooltipComponents.accept(ConstantComponents.NOT_BOUND);
        }
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, ServerLevel level, @NotNull Entity entity, EquipmentSlot slot) {
        if (level.isClientSide()) return;
        if ((entity.tickCount + entity.getId()) % 100 != 0) return;
        if (this.isBound(stack)) {
            int id = this.getBoundEntityId(stack);
            Entity boundEntity = level.getEntity(id);
            if (boundEntity == null) {
                this.setBoundEntityId(stack, -1);
            }
        }
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return this.isBound(stack) || super.isFoil(stack);
    }

    public boolean isBound(ItemStack stack) {
        return this.getBoundEntityId(stack) >= 0;
    }

    public int getBoundEntityId(ItemStack stack) {
        return stack.getOrDefault(ModDataComponents.BOUND_ENTITY.get(), -1);
    }

    public void setBoundEntityId(ItemStack stack, int id) {
        stack.set(ModDataComponents.BOUND_ENTITY.get(), id);
    }

}

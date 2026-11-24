package tech.alexnijjar.endermanoverhaul.common.items.pearls;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantComponents;
import tech.alexnijjar.endermanoverhaul.common.entities.projectiles.ThrownBubblePearl;
import tech.alexnijjar.endermanoverhaul.common.registry.ModSoundEvents;

import java.util.function.Consumer;

public class BubblePearlItem extends EnderpearlItem {
    public BubblePearlItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f));
        player.getCooldowns().addCooldown(itemStack, 20);
        if (!level.isClientSide()) {
            ThrownBubblePearl pearl = new ThrownBubblePearl(level, player);
            pearl.setItem(itemStack);
            pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 0.4f, 0.01f);
            level.addFreshEntity(pearl);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSoundEvents.BUBBLE_PEARL_THROWN.get(), SoundSource.PLAYERS, 1.0f, level.getRandom().nextFloat() * 0.4f + 0.8f);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.accept(ConstantComponents.BUBBLE_PEARL_TOOLTIP);
    }
}

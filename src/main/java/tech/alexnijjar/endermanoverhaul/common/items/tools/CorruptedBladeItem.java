package tech.alexnijjar.endermanoverhaul.common.items.tools;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantComponents;
import tech.alexnijjar.endermanoverhaul.common.utils.ModUtils;

import java.util.function.Consumer;

public class CorruptedBladeItem extends Item {
    public static final TagKey<Item> REPAIR_ITEMS = TagKey.create(Registries.ITEM,
        Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "repairs_corrupted_blade"));

    public CorruptedBladeItem(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
        if (attacker.typeHolder().is(ConventionalEntityTypeTags.TELEPORTING_NOT_SUPPORTED)) return;
        if (attacker.level().getRandom().nextInt(4) != 0) {
            ModUtils.teleportTarget(target.level(), target, 32);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.accept(ConstantComponents.CORRUPTED_BLADE_TOOLTIP);
    }
}

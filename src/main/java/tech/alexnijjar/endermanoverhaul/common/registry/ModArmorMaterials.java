package tech.alexnijjar.endermanoverhaul.common.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public final class ModArmorMaterials {
    private ModArmorMaterials() {}

    public static final ArmorMaterial HOOD = new ArmorMaterial(15, Map.of(
        ArmorType.BOOTS, 1,
        ArmorType.LEGGINGS, 4,
        ArmorType.CHESTPLATE, 5,
        ArmorType.HELMET, 2,
        ArmorType.BODY, 4
    ), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0, 0, ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssets.CHAINMAIL);
}

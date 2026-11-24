package tech.alexnijjar.endermanoverhaul.common.registry;

import com.teamresourceful.resourcefullib.common.item.tabs.ResourcefulCreativeModeTab;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.equipment.ArmorType;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.items.HoodItem;
import tech.alexnijjar.endermanoverhaul.common.items.pearls.*;
import tech.alexnijjar.endermanoverhaul.common.items.tools.CorruptedBladeItem;
import tech.alexnijjar.endermanoverhaul.common.items.tools.CorruptedShieldItem;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public class ModItems {
    public static final ResourcefulRegistry<Item> ITEMS = ResourcefulRegistries.create(BuiltInRegistries.ITEM, EndermanOverhaul.MOD_ID);
    public static final ResourcefulRegistry<Item> PEARLS = ResourcefulRegistries.create(ITEMS);
    public static final ResourcefulRegistry<Item> SPAWN_EGGS = ResourcefulRegistries.create(ITEMS);
    public static final ResourcefulRegistry<CreativeModeTab> TABS = ResourcefulRegistries.create(BuiltInRegistries.CREATIVE_MODE_TAB, EndermanOverhaul.MOD_ID);
    public static final RegistryEntry<CreativeModeTab> TAB = TABS.register("main", () -> new ResourcefulCreativeModeTab(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "main"))
        .setItemIcon(ModItems.CORRUPTED_PEARL)
        .addRegistry(ITEMS)
        .build());

    public static final RegistryEntry<Item> BADLANDS_ENDERMAN_SPAWN_EGG = spawnEgg("badlands_enderman_spawn_egg", ModEntityTypes.BADLANDS_ENDERMAN);
    public static final RegistryEntry<Item> CAVE_ENDERMAN_SPAWN_EGG = spawnEgg("cave_enderman_spawn_egg", ModEntityTypes.CAVE_ENDERMAN);
    public static final RegistryEntry<Item> CRIMSON_FOREST_ENDERMAN_SPAWN_EGG = spawnEgg("crimson_forest_enderman_spawn_egg", ModEntityTypes.CRIMSON_FOREST_ENDERMAN);
    public static final RegistryEntry<Item> DARK_OAK_ENDERMAN_SPAWN_EGG = spawnEgg("dark_oak_enderman_spawn_egg", ModEntityTypes.DARK_OAK_ENDERMAN);
    public static final RegistryEntry<Item> DESERT_ENDERMAN_SPAWN_EGG = spawnEgg("desert_enderman_spawn_egg", ModEntityTypes.DESERT_ENDERMAN);
    public static final RegistryEntry<Item> END_ENDERMAN_SPAWN_EGG = spawnEgg("end_enderman_spawn_egg", ModEntityTypes.END_ENDERMAN);
    public static final RegistryEntry<Item> END_ISLANDS_ENDERMAN_SPAWN_EGG = spawnEgg("end_islands_enderman_spawn_egg", ModEntityTypes.END_ISLANDS_ENDERMAN);
    public static final RegistryEntry<Item> FLOWER_FIELDS_ENDERMAN_SPAWN_EGG = spawnEgg("flower_fields_enderman_spawn_egg", ModEntityTypes.FLOWER_FIELDS_ENDERMAN);
    public static final RegistryEntry<Item> ICE_SPIKES_ENDERMAN_SPAWN_EGG = spawnEgg("ice_spikes_enderman_spawn_egg", ModEntityTypes.ICE_SPIKES_ENDERMAN);
    public static final RegistryEntry<Item> MUSHROOM_FIELDS_ENDERMAN_SPAWN_EGG = spawnEgg("mushroom_fields_enderman_spawn_egg", ModEntityTypes.MUSHROOM_FIELDS_ENDERMAN);
    public static final RegistryEntry<Item> NETHER_WASTES_ENDERMAN_SPAWN_EGG = spawnEgg("nether_wastes_enderman_spawn_egg", ModEntityTypes.NETHER_WASTES_ENDERMAN);
    public static final RegistryEntry<Item> CORAL_ENDERMAN_SPAWN_EGG = spawnEgg("coral_enderman_spawn_egg", ModEntityTypes.CORAL_ENDERMAN);
    public static final RegistryEntry<Item> SAVANNA_ENDERMAN_SPAWN_EGG = spawnEgg("savanna_enderman_spawn_egg", ModEntityTypes.SAVANNA_ENDERMAN);
    public static final RegistryEntry<Item> SNOWY_ENDERMAN_SPAWN_EGG = spawnEgg("snowy_enderman_spawn_egg", ModEntityTypes.SNOWY_ENDERMAN);
    public static final RegistryEntry<Item> SOULSAND_VALLEY_ENDERMAN_SPAWN_EGG = spawnEgg("soulsand_valley_enderman_spawn_egg", ModEntityTypes.SOULSAND_VALLEY_ENDERMAN);
    public static final RegistryEntry<Item> SWAMP_ENDERMAN_SPAWN_EGG = spawnEgg("swamp_enderman_spawn_egg", ModEntityTypes.SWAMP_ENDERMAN);
    public static final RegistryEntry<Item> WARPED_FOREST_ENDERMAN_SPAWN_EGG = spawnEgg("warped_forest_enderman_spawn_egg", ModEntityTypes.WARPED_FOREST_ENDERMAN);
    public static final RegistryEntry<Item> WINDSWEPT_HILLS_ENDERMAN_SPAWN_EGG = spawnEgg("windswept_hills_enderman_spawn_egg", ModEntityTypes.WINDSWEPT_HILLS_ENDERMAN);

    public static final RegistryEntry<Item> SCARAB_SPAWN_EGG = spawnEgg("scarab_spawn_egg", ModEntityTypes.SCARAB);
    public static final RegistryEntry<Item> SPIRIT_SPAWN_EGG = spawnEgg("spirit_spawn_egg", ModEntityTypes.SPIRIT);

    public static final RegistryEntry<Item> TINY_SKULL = ITEMS.register("tiny_skull", () -> new BlockItem(ModBlocks.TINY_SKULL.get(), properties("tiny_skull").useBlockDescriptionPrefix().stacksTo(16)));

    public static final RegistryEntry<Item> BADLANDS_HOOD = hood("badlands_hood");
    public static final RegistryEntry<Item> SAVANNAS_HOOD = hood("savanna_hood");
    public static final RegistryEntry<Item> SNOWY_HOOD = hood("snowy_hood");

    public static final RegistryEntry<Item> CORRUPTED_PEARL = PEARLS.register("corrupted_pearl", () -> new CorruptedPearlItem(properties("corrupted_pearl").stacksTo(16)));
    public static final RegistryEntry<Item> SOUL_PEARL = PEARLS.register("soul_pearl", () -> new SoulPearlItem(properties("soul_pearl").stacksTo(16).component(ModDataComponents.BOUND_ENTITY.get(), -1)));
    public static final RegistryEntry<Item> ANCIENT_PEARL = PEARLS.register("ancient_pearl", () -> new AncientPearlItem(properties("ancient_pearl").stacksTo(16)));
    public static final RegistryEntry<Item> BUBBLE_PEARL = PEARLS.register("bubble_pearl", () -> new BubblePearlItem(properties("bubble_pearl").stacksTo(16)));
    public static final RegistryEntry<Item> SUMMONER_PEARL = PEARLS.register("summoner_pearl", () -> new SummonerPearlItem(properties("summoner_pearl").stacksTo(16)));
    public static final RegistryEntry<Item> ICY_PEARL = PEARLS.register("icy_pearl", () -> new IcyPearlItem(properties("icy_pearl").stacksTo(16)));
    public static final RegistryEntry<Item> CRIMSON_PEARL = PEARLS.register("crimson_pearl", () -> new CrimsonPearlItem(properties("crimson_pearl").stacksTo(16)));
    public static final RegistryEntry<Item> WARPED_PEARL = PEARLS.register("warped_pearl", () -> new WarpedPearlItem(properties("warped_pearl").stacksTo(16)));

    public static final RegistryEntry<Item> ENDERMAN_TOOTH = ITEMS.register("enderman_tooth", () -> new Item(properties("enderman_tooth")));
    public static final RegistryEntry<Item> CORRUPTED_BLADE = ITEMS.register("corrupted_blade", () -> new CorruptedBladeItem(properties("corrupted_blade").sword(ToolMaterial.IRON, 3, -2.4f).enchantable(22).repairable(CorruptedBladeItem.REPAIR_ITEMS)));
    public static final RegistryEntry<Item> CORRUPTED_SHIELD = ITEMS.register("corrupted_shield", () -> new CorruptedShieldItem(properties("corrupted_shield").durability(672)
            .delayedComponent(DataComponents.BLOCKS_ATTACKS, registries -> new BlocksAttacks(
                0.25f, 1f, List.of(new BlocksAttacks.DamageReduction(90, Optional.empty(), 0, 1)),
                new BlocksAttacks.ItemDamageFunction(3, 1, 1), Optional.of(registries.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                Optional.of(SoundEvents.SHIELD_BLOCK), Optional.of(SoundEvents.SHIELD_BREAK)))
            .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
            .repairable(ENDERMAN_TOOTH.get())));

    private static RegistryEntry<Item> hood(String name) {
        return ITEMS.register(name, () -> new HoodItem(name, properties(name)
            .humanoidArmor(ModArmorMaterials.HOOD, ArmorType.CHESTPLATE)
            .durability(ArmorType.HELMET.getDurability(ModArmorMaterials.HOOD.durability()))));
    }

    private static RegistryEntry<Item> spawnEgg(String name, RegistryEntry<? extends EntityType<?>> entityType) {
        return SPAWN_EGGS.register(name, () -> new SpawnEggItem(properties(name).spawnEgg(entityType.get())));
    }

    private static Item.Properties properties(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, name);
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id));
    }
}

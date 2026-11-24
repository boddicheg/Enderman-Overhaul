package tech.alexnijjar.endermanoverhaul.common.registry;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.MobSpawnSettings;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;

import java.util.function.Predicate;

import static tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes.*;

public final class EndermanSpawns {
    private EndermanSpawns() {}

    public static void register() {
        addSpawn(BADLANDS_ENDERMAN.get(), "badlands_spawns", 10, 1, 4);
        addChargedSpawn(CAVE_ENDERMAN.get(), "cave_spawns", 2, 1, 1, 0.5, 0.05);
        addChargedSpawn(CORAL_ENDERMAN.get(), "coral_spawns", 5, 1, 2, 0.12, 0.7);
        addSpawn(CRIMSON_FOREST_ENDERMAN.get(), "crimson_forest_spawns", 2, 1, 2);
        addSpawn(DARK_OAK_ENDERMAN.get(), "dark_oak_spawns", 10, 1, 4);
        addSpawn(DESERT_ENDERMAN.get(), "desert_spawns", 10, 1, 4);
        addChargedSpawn(END_ENDERMAN.get(), "end_spawns", 2, 1, 2, 0.5, 0.05);
        addSpawn(END_ISLANDS_ENDERMAN.get(), "end_islands_spawns", 1, 1, 1);
        addSpawn(FLOWER_FIELDS_ENDERMAN.get(), "flower_fields_spawns", 5, 1, 4);
        addSpawn(ICE_SPIKES_ENDERMAN.get(), "ice_spikes_spawns", 10, 1, 4);
        addSpawn(MUSHROOM_FIELDS_ENDERMAN.get(), "mushroom_fields_spawns", 1, 1, 2);
        addChargedSpawn(NETHER_WASTES_ENDERMAN.get(), "nether_wastes_spawns", 1, 2, 4, 0.12, 0.7);
        addSpawn(SAVANNA_ENDERMAN.get(), "savanna_spawns", 10, 1, 4);
        addChargedSpawn(SNOWY_ENDERMAN.get(), "snowy_spawns", 10, 1, 2, 0.5, 0.05);
        addChargedSpawn(SOULSAND_VALLEY_ENDERMAN.get(), "soulsand_valley_spawns", 2, 1, 2, 0.15, 0.7);
        addSpawn(SWAMP_ENDERMAN.get(), "swamp_spawns", 10, 1, 4);
        addSpawn(WARPED_FOREST_ENDERMAN.get(), "warped_forest_spawns", 1, 4, 4);
        addSpawn(WINDSWEPT_HILLS_ENDERMAN.get(), "windswept_hills_spawns", 10, 1, 4);

        BiomeModifications.create(id("remove_vanilla_endermen"))
            .add(ModificationPhase.REMOVALS, tag("all_spawns"), context -> {
                var spawns = context.getMobSpawnSettings();
                spawns.removeSpawnsOfEntityType(EntityType.ENDERMAN);
                spawns.clearMobCharge(EntityType.ENDERMAN);
            });
    }

    private static void addSpawn(EntityType<?> type, String biomeTag, int weight, int minCount, int maxCount) {
        BiomeModifications.addSpawn(tag(biomeTag), type.getCategory(), type, weight, minCount, maxCount);
    }

    private static void addChargedSpawn(EntityType<?> type, String biomeTag, int weight, int minCount, int maxCount,
                                        double charge, double energyBudget) {
        BiomeModifications.create(BuiltInRegistries.ENTITY_TYPE.getKey(type))
            .add(ModificationPhase.ADDITIONS, tag(biomeTag), context -> {
                var spawns = context.getMobSpawnSettings();
                spawns.addSpawn(type.getCategory(), new MobSpawnSettings.SpawnerData(type, minCount, maxCount), weight);
                spawns.addMobCharge(type, charge, energyBudget);
            });
    }

    private static Predicate<BiomeSelectionContext> tag(String path) {
        return BiomeSelectors.tag(TagKey.create(Registries.BIOME, id(path)));
    }

    private static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, path);
    }
}

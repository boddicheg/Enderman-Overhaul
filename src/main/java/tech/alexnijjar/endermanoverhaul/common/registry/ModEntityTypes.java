package tech.alexnijjar.endermanoverhaul.common.registry;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.entities.*;
import tech.alexnijjar.endermanoverhaul.common.entities.pets.AxolotlPetEnderman;
import tech.alexnijjar.endermanoverhaul.common.entities.pets.HammerheadPetEnderman;
import tech.alexnijjar.endermanoverhaul.common.entities.pets.PetEnderman;
import tech.alexnijjar.endermanoverhaul.common.entities.projectiles.*;
import tech.alexnijjar.endermanoverhaul.common.entities.summons.Scarab;
import tech.alexnijjar.endermanoverhaul.common.entities.summons.Spirit;

public class ModEntityTypes {
    public static final ResourcefulRegistry<EntityType<?>> ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.ENTITY_TYPE, EndermanOverhaul.MOD_ID);
    public static final ResourcefulRegistry<EntityType<?>> ENDERMEN = ResourcefulRegistries.create(ENTITY_TYPES);
    public static final ResourcefulRegistry<EntityType<?>> PEARLS = ResourcefulRegistries.create(ENTITY_TYPES);

    public static final RegistryEntry<EntityType<BadlandsEnderman>> BADLANDS_ENDERMAN = ENDERMEN.register("badlands_enderman", () ->
        EntityType.Builder.of(BadlandsEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 3.0f)
            .eyeHeight(2.74f)
            .build(key("badlands_enderman")));

    public static final RegistryEntry<EntityType<CaveEnderman>> CAVE_ENDERMAN = ENDERMEN.register("cave_enderman", () ->
        EntityType.Builder.of(CaveEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.6f)
            .eyeHeight(2.45f)
            .build(key("cave_enderman")));

    public static final RegistryEntry<EntityType<CrimsonForestEnderman>> CRIMSON_FOREST_ENDERMAN = ENDERMEN.register("crimson_forest_enderman", () ->
        EntityType.Builder.of(CrimsonForestEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 3.1f)
            .fireImmune()
            .eyeHeight(2.95f)
            .build(key("crimson_forest_enderman")));

    public static final RegistryEntry<EntityType<DarkOakEnderman>> DARK_OAK_ENDERMAN = ENDERMEN.register("dark_oak_enderman", () ->
        EntityType.Builder.of(DarkOakEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.7f, 3.5f)
            .eyeHeight(2.95f)
            .build(key("dark_oak_enderman")));

    public static final RegistryEntry<EntityType<DesertEnderman>> DESERT_ENDERMAN = ENDERMEN.register("desert_enderman", () ->
        EntityType.Builder.of(DesertEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 3.1f)
            .eyeHeight(2.95f)
            .build(key("desert_enderman")));

    public static final RegistryEntry<EntityType<EndEnderman>> END_ENDERMAN = ENDERMEN.register("end_enderman", () ->
        EntityType.Builder.of(EndEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.5f)
            .fireImmune()
            .eyeHeight(2.35f)
            .build(key("end_enderman")));

    public static final RegistryEntry<EntityType<EndIslandsEnderman>> END_ISLANDS_ENDERMAN = ENDERMEN.register("end_islands_enderman", () ->
        EntityType.Builder.of(EndIslandsEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 4.4f)
            .fireImmune()
            .eyeHeight(4.25f)
            .build(key("end_islands_enderman")));

    public static final RegistryEntry<EntityType<FlowerFieldsEnderman>> FLOWER_FIELDS_ENDERMAN = ENDERMEN.register("flower_fields_enderman", () ->
        EntityType.Builder.of(FlowerFieldsEnderman::new, MobCategory.CREATURE)
            .sized(0.5f, 1.5f)
            .eyeHeight(1.15f)
            .build(key("flower_fields_enderman")));

    public static final RegistryEntry<EntityType<IceSpikesEnderman>> ICE_SPIKES_ENDERMAN = ENDERMEN.register("ice_spikes_enderman", () ->
        EntityType.Builder.of(IceSpikesEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 3.9f)
            .eyeHeight(3.55f)
            .build(key("ice_spikes_enderman")));

    public static final RegistryEntry<EntityType<MushroomFieldsEnderman>> MUSHROOM_FIELDS_ENDERMAN = ENDERMEN.register("mushroom_fields_enderman", () ->
        EntityType.Builder.of(MushroomFieldsEnderman::new, MobCategory.CREATURE)
            .sized(0.6f, 2.6f)
            .eyeHeight(2.45f)
            .build(key("mushroom_fields_enderman")));

    public static final RegistryEntry<EntityType<NetherWastesEnderman>> NETHER_WASTES_ENDERMAN = ENDERMEN.register("nether_wastes_enderman", () ->
        EntityType.Builder.of(NetherWastesEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.9f)
            .fireImmune()
            .eyeHeight(2.55f)
            .build(key("nether_wastes_enderman")));

    public static final RegistryEntry<EntityType<CoralEnderman>> CORAL_ENDERMAN = ENDERMEN.register("coral_enderman", () ->
        EntityType.Builder.of(CoralEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.0f)
            .eyeHeight(1.65f)
            .build(key("coral_enderman")));

    public static final RegistryEntry<EntityType<SavannaEnderman>> SAVANNA_ENDERMAN = ENDERMEN.register("savanna_enderman", () ->
        EntityType.Builder.of(SavannaEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.8f)
            .eyeHeight(2.45f)
            .build(key("savanna_enderman")));

    public static final RegistryEntry<EntityType<SnowyEnderman>> SNOWY_ENDERMAN = ENDERMEN.register("snowy_enderman", () ->
        EntityType.Builder.of(SnowyEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.8f)
            .eyeHeight(2.45f)
            .build(key("snowy_enderman")));

    public static final RegistryEntry<EntityType<SoulsandValleyEnderman>> SOULSAND_VALLEY_ENDERMAN = ENDERMEN.register("soulsand_valley_enderman", () ->
        EntityType.Builder.of(SoulsandValleyEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 2.5f)
            .fireImmune()
            .eyeHeight(2.15f)
            .build(key("soulsand_valley_enderman")));

    public static final RegistryEntry<EntityType<SwampEnderman>> SWAMP_ENDERMAN = ENDERMEN.register("swamp_enderman", () ->
        EntityType.Builder.of(SwampEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 3.8f)
            .eyeHeight(3.45f)
            .build(key("swamp_enderman")));

    public static final RegistryEntry<EntityType<WarpedForestEnderman>> WARPED_FOREST_ENDERMAN = ENDERMEN.register("warped_forest_enderman", () ->
        EntityType.Builder.of(WarpedForestEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.8f, 2.4f)
            .fireImmune()
            .eyeHeight(2.05f)
            .build(key("warped_forest_enderman")));

    public static final RegistryEntry<EntityType<WindsweptHillsEnderman>> WINDSWEPT_HILLS_ENDERMAN = ENDERMEN.register("windswept_hills_enderman", () ->
        EntityType.Builder.of(WindsweptHillsEnderman::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.6f, 4.1f)
            .eyeHeight(3.75f)
            .build(key("windswept_hills_enderman")));

    // Pets
    public static final RegistryEntry<EntityType<PetEnderman>> PET_ENDERMAN = ENTITY_TYPES.register("pet_enderman", () ->
        EntityType.Builder.<PetEnderman>of(PetEnderman::new, MobCategory.CREATURE)
            .sized(0.8f, 2.8f)
            .eyeHeight(2.45f)
            .build(key("pet_enderman")));

    public static final RegistryEntry<EntityType<HammerheadPetEnderman>> HAMMERHEAD_PET_ENDERMAN = ENTITY_TYPES.register("hammerhead_pet_enderman", () ->
        EntityType.Builder.<HammerheadPetEnderman>of(HammerheadPetEnderman::new, MobCategory.CREATURE)
            .sized(1.0f, 2.6f)
            .eyeHeight(2.45f)
            .build(key("hammerhead_pet_enderman")));

    public static final RegistryEntry<EntityType<AxolotlPetEnderman>> AXOLOTL_PET_ENDERMAN = ENTITY_TYPES.register("axolotl_pet_enderman", () ->
        EntityType.Builder.<AxolotlPetEnderman>of(AxolotlPetEnderman::new, MobCategory.CREATURE)
            .sized(0.8f, 2.7f)
            .eyeHeight(2.35f)
            .build(key("axolotl_pet_enderman")));

    // Summons
    public static final RegistryEntry<EntityType<Scarab>> SCARAB = ENTITY_TYPES.register("scarab", () ->
        EntityType.Builder.of(Scarab::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.3f, 0.5f)
            .eyeHeight(0.13f)
            .passengerAttachments(0.2375f)
            .clientTrackingRange(8)
            .build(key("scarab")));

    public static final RegistryEntry<EntityType<Spirit>> SPIRIT = ENTITY_TYPES.register("spirit", () ->
        EntityType.Builder.of(Spirit::new, MobCategory.MONSTER)
            .notInPeaceful()
            .sized(0.3f, 0.3f)
            .fireImmune()
            .eyeHeight(0.51875f)
            .passengerAttachments(0.7375f)
            .ridingOffset(0.04f)
            .clientTrackingRange(8)
            .build(key("spirit")));

    // Projectiles
    public static final RegistryEntry<EntityType<EnderBullet>> ENDER_BULLET = ENTITY_TYPES.register("ender_bullet", () ->
        EntityType.Builder.<EnderBullet>of(EnderBullet::new, MobCategory.MISC)
            .sized(0.3125f, 0.3125f)
            .clientTrackingRange(8)
            .build(key("ender_bullet")));

    public static final RegistryEntry<EntityType<ThrownCorruptedPearl>> CORRUPTED_PEARL = PEARLS.register("corrupted_pearl", () ->
        EntityType.Builder.<ThrownCorruptedPearl>of(ThrownCorruptedPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("corrupted_pearl")));

    public static final RegistryEntry<EntityType<ThrownSoulPearl>> SOUL_PEARL = PEARLS.register("soul_pearl", () ->
        EntityType.Builder.<ThrownSoulPearl>of(ThrownSoulPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("soul_pearl")));

    public static final RegistryEntry<EntityType<ThrownAncientPearl>> ANCIENT_PEARL = PEARLS.register("ancient_pearl", () ->
        EntityType.Builder.<ThrownAncientPearl>of(ThrownAncientPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("ancient_pearl")));

    public static final RegistryEntry<EntityType<ThrownBubblePearl>> BUBBLE_PEARL = PEARLS.register("bubble_pearl", () ->
        EntityType.Builder.<ThrownBubblePearl>of(ThrownBubblePearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("bubble_pearl")));

    public static final RegistryEntry<EntityType<ThrownSummonerPearl>> SUMMONER_PEARL = PEARLS.register("summoner_pearl", () ->
        EntityType.Builder.<ThrownSummonerPearl>of(ThrownSummonerPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("summoner_pearl")));

    public static final RegistryEntry<EntityType<ThrownIcyPearl>> ICY_PEARL = PEARLS.register("icy_pearl", () ->
        EntityType.Builder.<ThrownIcyPearl>of(ThrownIcyPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("icy_pearl")));

    public static final RegistryEntry<EntityType<ThrownCrimsonPearl>> CRIMSON_PEARL = PEARLS.register("crimson_pearl", () ->
        EntityType.Builder.<ThrownCrimsonPearl>of(ThrownCrimsonPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("crimson_pearl")));

    public static final RegistryEntry<EntityType<ThrownWarpedPearl>> WARPED_PEARL = PEARLS.register("warped_pearl", () ->
        EntityType.Builder.<ThrownWarpedPearl>of(ThrownWarpedPearl::new, MobCategory.MISC)
            .sized(0.25f, 0.25f)
            .clientTrackingRange(4)
            .updateInterval(10)
            .build(key("warped_pearl")));

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(BADLANDS_ENDERMAN.get(), BadlandsEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CAVE_ENDERMAN.get(), CaveEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CRIMSON_FOREST_ENDERMAN.get(), CrimsonForestEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DARK_OAK_ENDERMAN.get(), DarkOakEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(DESERT_ENDERMAN.get(), DesertEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(END_ENDERMAN.get(), EndEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(END_ISLANDS_ENDERMAN.get(), EndIslandsEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(FLOWER_FIELDS_ENDERMAN.get(), FlowerFieldsEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(ICE_SPIKES_ENDERMAN.get(), IceSpikesEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(MUSHROOM_FIELDS_ENDERMAN.get(), MushroomFieldsEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(NETHER_WASTES_ENDERMAN.get(), NetherWastesEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(CORAL_ENDERMAN.get(), CoralEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SAVANNA_ENDERMAN.get(), SavannaEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SNOWY_ENDERMAN.get(), SnowyEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SOULSAND_VALLEY_ENDERMAN.get(), SoulsandValleyEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SWAMP_ENDERMAN.get(), SwampEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WARPED_FOREST_ENDERMAN.get(), WarpedForestEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(WINDSWEPT_HILLS_ENDERMAN.get(), WindsweptHillsEnderman.createAttributes().build());

        // Pets
        FabricDefaultAttributeRegistry.register(PET_ENDERMAN.get(), PetEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(HAMMERHEAD_PET_ENDERMAN.get(), HammerheadPetEnderman.createAttributes().build());
        FabricDefaultAttributeRegistry.register(AXOLOTL_PET_ENDERMAN.get(), AxolotlPetEnderman.createAttributes().build());

        // Summons
        FabricDefaultAttributeRegistry.register(SCARAB.get(), Scarab.createAttributes().build());
        FabricDefaultAttributeRegistry.register(SPIRIT.get(), Spirit.createAttributes().build());
    }

    public static void registerSpawnPlacements() {
        SpawnPlacements.register(BADLANDS_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BadlandsEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(CAVE_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CaveEnderman::checkSpawnRules);
        SpawnPlacements.register(CRIMSON_FOREST_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CrimsonForestEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(DARK_OAK_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DarkOakEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(DESERT_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DesertEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(END_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(END_ISLANDS_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndIslandsEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(FLOWER_FIELDS_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FlowerFieldsEnderman::checkMobSpawnRules);
        SpawnPlacements.register(ICE_SPIKES_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, IceSpikesEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(MUSHROOM_FIELDS_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, MushroomFieldsEnderman::checkMobSpawnRules);
        SpawnPlacements.register(NETHER_WASTES_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, NetherWastesEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(CORAL_ENDERMAN.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CoralEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(SAVANNA_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SavannaEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(SNOWY_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SnowyEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(SOULSAND_VALLEY_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SoulsandValleyEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(SWAMP_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SwampEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(WARPED_FOREST_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WarpedForestEnderman::checkMonsterSpawnRules);
        SpawnPlacements.register(WINDSWEPT_HILLS_ENDERMAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WindsweptHillsEnderman::checkMonsterSpawnRules);

        // Summons
        SpawnPlacements.register(SCARAB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
        SpawnPlacements.register(SPIRIT.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }
    private static ResourceKey<EntityType<?>> key(String name) {
        return ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, name));
    }
}

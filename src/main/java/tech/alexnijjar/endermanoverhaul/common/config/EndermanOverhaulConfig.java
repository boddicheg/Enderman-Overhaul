package tech.alexnijjar.endermanoverhaul.common.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigInfo;
import tech.alexnijjar.endermanoverhaul.client.config.info.EndermanOverhaulConfigInfo;

@Config("endermanoverhaul")
@ConfigInfo.Provider(EndermanOverhaulConfigInfo.class)
public final class EndermanOverhaulConfig {

    @ConfigEntry(
        id = "allowPickingUpBlocks",
        translation = "config.endermanoverhaul.allowPickingUpBlocks"
    )
    public static boolean allowPickingUpBlocks = true;

    @ConfigEntry(
        id = "friendlyEndermanTeleport",
        translation = "config.endermanoverhaul.friendlyEndermanTeleport"
    )
    public static boolean friendlyEndermanTeleport = true;

    @ConfigEntry(
        id = "friendlyEndermanDespawn",
        translation = "config.endermanoverhaul.friendlyEndermanDespawn"
    )
    public static boolean friendlyEndermanDespawn = true;

    @ConfigEntry(
        id = "allowSpawning",
        translation = "config.endermanoverhaul.allowSpawning"
    )
    public static boolean allowSpawning = true;

    @ConfigEntry(
        id = "spawnBadlandsEnderman",
        translation = "config.endermanoverhaul.spawnBadlandsEnderman"
    )
    public static boolean spawnBadlandsEnderman = true;

    @ConfigEntry(
        id = "spawnCaveEnderman",
        translation = "config.endermanoverhaul.spawnCaveEnderman"
    )
    public static boolean spawnCaveEnderman = true;

    @ConfigEntry(
        id = "spawnCrimsonForestEnderman",
        translation = "config.endermanoverhaul.spawnCrimsonForestEnderman"
    )
    public static boolean spawnCrimsonForestEnderman = true;

    @ConfigEntry(
        id = "spawnDarkOakEnderman",
        translation = "config.endermanoverhaul.spawnDarkOakEnderman"
    )
    public static boolean spawnDarkOakEnderman = true;

    @ConfigEntry(
        id = "spawnDesertEnderman",
        translation = "config.endermanoverhaul.spawnDesertEnderman"
    )
    public static boolean spawnDesertEnderman = true;

    @ConfigEntry(
        id = "spawnEndEnderman",
        translation = "config.endermanoverhaul.spawnEndEnderman"
    )
    public static boolean spawnEndEnderman = true;

    @ConfigEntry(
        id = "spawnEndIslandsEnderman",
        translation = "config.endermanoverhaul.spawnEndIslandsEnderman"
    )
    public static boolean spawnEndIslandsEnderman = true;

    @ConfigEntry(
        id = "spawnFlowerFieldsEnderman",
        translation = "config.endermanoverhaul.spawnFlowerFieldsEnderman"
    )
    public static boolean spawnFlowerFieldsEnderman = true;

    @ConfigEntry(
        id = "spawnIceSpikesEnderman",
        translation = "config.endermanoverhaul.spawnIceSpikesEnderman"
    )
    public static boolean spawnIceSpikesEnderman = true;

    @ConfigEntry(
        id = "spawnMushroomFieldsEnderman",
        translation = "config.endermanoverhaul.spawnMushroomFieldsEnderman"
    )
    public static boolean spawnMushroomFieldsEnderman = true;

    @ConfigEntry(
        id = "spawnNetherWastesEnderman",
        translation = "config.endermanoverhaul.spawnNetherWastesEnderman"
    )
    public static boolean spawnNetherWastesEnderman = true;

    @ConfigEntry(
        id = "spawnCoralEnderman",
        translation = "config.endermanoverhaul.spawnCoralEnderman"
    )
    public static boolean spawnCoralEnderman = true;

    @ConfigEntry(
        id = "spawnSavannaEnderman",
        translation = "config.endermanoverhaul.spawnSavannaEnderman"
    )
    public static boolean spawnSavannaEnderman = true;

    @ConfigEntry(
        id = "spawnSnowyEnderman",
        translation = "config.endermanoverhaul.spawnSnowyEnderman"
    )
    public static boolean spawnSnowyEnderman = true;

    @ConfigEntry(
        id = "spawnSoulsandValleyEnderman",
        translation = "config.endermanoverhaul.spawnSoulsandValleyEnderman"
    )
    public static boolean spawnSoulsandValleyEnderman = true;

    @ConfigEntry(
        id = "spawnSwampEnderman",
        translation = "config.endermanoverhaul.spawnSwampEnderman"
    )
    public static boolean spawnSwampEnderman = true;

    @ConfigEntry(
        id = "spawnWarpedForestEnderman",
        translation = "config.endermanoverhaul.spawnWarpedForestEnderman"
    )
    public static boolean spawnWarpedForestEnderman = true;

    @ConfigEntry(
        id = "spawnWindsweptHillsEnderman",
        translation = "config.endermanoverhaul.spawnWindsweptHillsEnderman"
    )
    public static boolean spawnWindsweptHillsEnderman = true;

    @ConfigEntry(
        id = "endEndermanTeleportChance",
        translation = "config.endermanoverhaul.endEndermanTeleportChance"
    )
    @Comment("The chance that an End Enderman will teleport you when it hits you")
    public static float endEndermanTeleportChance = 0.5f;
}

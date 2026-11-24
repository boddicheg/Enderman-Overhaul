package tech.alexnijjar.endermanoverhaul.datagen.provider.client;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.BlockItem;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantComponents;
import tech.alexnijjar.endermanoverhaul.common.registry.ModBlocks;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ModLangProvider extends FabricLanguageProvider {
    public ModLangProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider registries, TranslationBuilder translations) {
        ModBlocks.BLOCKS.stream()
            .forEach(entry -> translations.add(entry.get(), displayName(entry.getId().getPath())));

        ModItems.ITEMS.stream()
            .filter(i -> !(i.get() instanceof BlockItem))
            .forEach(entry -> translations.add(entry.get(), displayName(entry.getId().getPath())));

        ModEntityTypes.ENTITY_TYPES.stream()
            .forEach(entry -> translations.add(entry.get(), displayName(entry.getId().getPath())));

        translations.add("itemGroup.endermanoverhaul.main", "Enderman Overhaul");

        translations.add(ConstantComponents.CORRUPTED_PEARL_TOOLTIP.getString(), "Teleports targets to a random location");
        translations.add(ConstantComponents.SOUL_PEARL_TOOLTIP_1.getString(), "Crouch-right-click to bind an entity");
        translations.add(ConstantComponents.SOUL_PEARL_TOOLTIP_2.getString(), "Teleports the bound entity to the target location");
        translations.add(ConstantComponents.ANCIENT_PEARL_TOOLTIP_1.getString(), "Summons a friendly enderman to fight for you");
        translations.add(ConstantComponents.ANCIENT_PEARL_TOOLTIP_2.getString(), "Crouch-right-click a summoned enderman to retrieve the pearl");
        translations.add(ConstantComponents.BUBBLE_PEARL_TOOLTIP.getString(), "Accurate Ender Pearl with no gravity");
        translations.add(ConstantComponents.ICY_PEARL_TOOLTIP.getString(), "Freezes nearby targets on hit");
        translations.add(ConstantComponents.SUMMONER_PEARL_TOOLTIP.getString(), "Teleports nearby targets on hit");
        translations.add(ConstantComponents.CRIMSON_PEARL_TOOLTIP.getString(), "Applies Strength II when teleporting");
        translations.add(ConstantComponents.WARPED_PEARL_TOOLTIP.getString(), "Applies Resistance II when teleporting");

        translations.add(ConstantComponents.HOOD_TOOLTIP.getString(), "Allows you to look at endermen without angering them");

        translations.add(ConstantComponents.CORRUPTED_BLADE_TOOLTIP.getString(), "Teleports targets randomly");
        translations.add(ConstantComponents.CORRUPTED_SHIELD_TOOLTIP.getString(), "Teleports attackers randomly");

        translations.add("tooltip.endermanoverhaul.bound_to", "Bound to: %s");
        translations.add("tooltip.endermanoverhaul.ancient_pet", "Pet health: %s");
        translations.add(ConstantComponents.NOT_BOUND.getString(), "Not bound");

        translations.add("tag.item.endermanoverhaul.ender_pearls", "Ender Pearls");

        translations.add("subtitles.endermanoverhaul.entity.cave_enderman.ambient", "Cave Enderman vwoops");
        translations.add("subtitles.endermanoverhaul.entity.cave_enderman.hurt", "Cave Enderman hurts");
        translations.add("subtitles.endermanoverhaul.entity.plant_enderman.ambient", "Plant Enderman vwoops");
        translations.add("subtitles.endermanoverhaul.entity.plant_enderman.hurt", "Plant Enderman hurts");
        translations.add("subtitles.endermanoverhaul.entity.tall_enderman.ambient", "Tall Enderman vwoops");
        translations.add("subtitles.endermanoverhaul.entity.tall_enderman.death", "Tall Enderman dies");
        translations.add("subtitles.endermanoverhaul.entity.tall_enderman.stare", "Tall Enderman cries out");

        translations.add("subtitles.endermanoverhaul.entity.flower_fields_enderman.ambient", "Flower Fields Enderman vwoops");
        translations.add("subtitles.endermanoverhaul.entity.flower_fields_enderman.hurt", "Flower Fields Enderman hurts");
        translations.add("subtitles.endermanoverhaul.entity.flower_fields_enderman.death", "Flower Fields Enderman dies");

        translations.add("subtitles.endermanoverhaul.entity.dark_oak_enderman.ambient", "Dark Oak Enderman vwoops");
        translations.add("subtitles.endermanoverhaul.entity.dark_oak_enderman.darkness", "Dark Oak Enderman darkens");
        translations.add("subtitles.endermanoverhaul.entity.dark_oak_enderman.stare", "Dark Oak Enderman cries out");

        translations.add("subtitles.endermanoverhaul.entity.ancient_pearl.hit", "Ancient Pearl Hits");
        translations.add("subtitles.endermanoverhaul.entity.bubble_pearl.hit", "Bubble Pearl Pops");
        translations.add("subtitles.endermanoverhaul.entity.bubble_pearl.thrown", "Bubble Pearl Thrown");
        translations.add("subtitles.endermanoverhaul.entity.corrupted_pearl.hit", "Corrupted Pearl Hits");
        translations.add("subtitles.endermanoverhaul.entity.soul_pearl.hit", "Soul Pearl Hits");
        translations.add("subtitles.endermanoverhaul.entity.summoner_pearl.hit", "Summoner Pearl Hits");
        translations.add("subtitles.endermanoverhaul.entity.icy_pearl.hit", "Icy Pearl Hits");

        translations.add("config.endermanoverhaul.title", "Enderman Overhaul");
        translations.add("config.endermanoverhaul.client.title", "Enderman Overhaul Client");
        translations.add("config.endermanoverhaul.description", "Enderman overhaul adds biome-specific Enderman!");
        translations.add("config.endermanoverhaul.clientConfig", "Client Config");
        translations.add("config.endermanoverhaul.allowPickingUpBlocks", "Allow Picking Up Blocks");
        translations.add("config.endermanoverhaul.friendlyEndermanTeleport", "Friendly Enderman Teleport");
        translations.add("config.endermanoverhaul.friendlyEndermanDespawn", "Friendly Enderman Despawn");

        translations.add("config.endermanoverhaul.replaceDefaultEnderman", "Replace Default Enderman");
        translations.add("config.endermanoverhaul.flashScreen", "Flash Screen");
        translations.add("config.endermanoverhaul.allowSpawning", "Allow Spawning");
        translations.add("config.endermanoverhaul.spawnBadlandsEnderman", "Spawn Badlands Enderman");
        translations.add("config.endermanoverhaul.spawnCaveEnderman", "Spawn Cave Enderman");
        translations.add("config.endermanoverhaul.spawnCrimsonForestEnderman", "Spawn Crimson Forest Enderman");
        translations.add("config.endermanoverhaul.spawnDarkOakEnderman", "Spawn Dark Oak Enderman");
        translations.add("config.endermanoverhaul.spawnDesertEnderman", "Spawn Desert Enderman");
        translations.add("config.endermanoverhaul.spawnEndEnderman", "Spawn End Enderman");
        translations.add("config.endermanoverhaul.spawnEndIslandsEnderman", "Spawn End Islands Enderman");
        translations.add("config.endermanoverhaul.spawnFlowerFieldsEnderman", "Spawn Flower Fields Enderman");
        translations.add("config.endermanoverhaul.spawnIceSpikesEnderman", "Spawn Ice Spikes Enderman");
        translations.add("config.endermanoverhaul.spawnMushroomFieldsEnderman", "Spawn Mushroom Fields Enderman");
        translations.add("config.endermanoverhaul.spawnNetherWastesEnderman", "Spawn Nether Wastes Enderman");
        translations.add("config.endermanoverhaul.spawnCoralEnderman", "Spawn Coral Enderman");
        translations.add("config.endermanoverhaul.spawnSavannaEnderman", "Spawn Savanna Enderman");
        translations.add("config.endermanoverhaul.spawnSnowyEnderman", "Spawn Snowy Enderman");
        translations.add("config.endermanoverhaul.spawnSoulsandValleyEnderman", "Spawn Soulsand Valley Enderman");
        translations.add("config.endermanoverhaul.spawnSwampEnderman", "Spawn Swamp Enderman");
        translations.add("config.endermanoverhaul.spawnWarpedForestEnderman", "Spawn Warped Forest Enderman");
        translations.add("config.endermanoverhaul.spawnWindsweptHillsEnderman", "Spawn Windswept Hills Enderman");
        translations.add("config.endermanoverhaul.endEndermanTeleportChance", "End Enderman Teleport Chance");
    }

    private static String displayName(String path) {
        return Arrays.stream(path.split("_"))
            .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
            .collect(Collectors.joining(" "));
    }
}

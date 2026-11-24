package tech.alexnijjar.endermanoverhaul.datagen.provider.server;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.BlockTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.common.tags.ModBlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        builder(ModBlockTags.CAVE_ENDERMAN_HOLDEABLE)
            .addOptionalTag(ConventionalBlockTags.COAL_ORES)
            .addOptionalTag(ConventionalBlockTags.IRON_ORES)
            .addOptionalTag(ConventionalBlockTags.DIAMOND_ORES)
            .addOptionalTag(ConventionalBlockTags.GOLD_ORES)
            .addOptionalTag(ConventionalBlockTags.REDSTONE_ORES)
            .addOptionalTag(ConventionalBlockTags.LAPIS_ORES)
            .addOptionalTag(ConventionalBlockTags.EMERALD_ORES)
            .addOptionalTag(ConventionalBlockTags.COPPER_ORES)
            .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD);
    }
}

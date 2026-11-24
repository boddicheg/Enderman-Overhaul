package tech.alexnijjar.endermanoverhaul.datagen.provider.server;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.BlockTagsProvider;
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
        valueLookupBuilder(ModBlockTags.CAVE_ENDERMAN_HOLDEABLE)
            .addOptionalTag(BlockTags.COAL_ORES)
            .addOptionalTag(BlockTags.IRON_ORES)
            .addOptionalTag(BlockTags.DIAMOND_ORES)
            .addOptionalTag(BlockTags.GOLD_ORES)
            .addOptionalTag(BlockTags.REDSTONE_ORES)
            .addOptionalTag(BlockTags.LAPIS_ORES)
            .addOptionalTag(BlockTags.EMERALD_ORES)
            .addOptionalTag(BlockTags.COPPER_ORES)
            .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD);
    }
}

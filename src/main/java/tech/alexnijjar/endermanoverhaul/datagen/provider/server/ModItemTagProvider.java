package tech.alexnijjar.endermanoverhaul.datagen.provider.server;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.ItemTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        ModItems.PEARLS.stream().forEach(pearl -> builder(ConventionalItemTags.ENDER_PEARLS).add(pearl.get().builtInRegistryHolder().key()));
    }
}

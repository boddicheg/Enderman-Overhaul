package tech.alexnijjar.endermanoverhaul.datagen.provider.server;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider.EntityTypeTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;
import tech.alexnijjar.endermanoverhaul.common.tags.ModEntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        ModEntityTypes.PEARLS.stream().forEach(pearl -> builder(ModEntityTypeTags.ENDER_PEARLS).add(pearl.get().builtInRegistryHolder().key()));
        builder(EntityTypeTags.CAN_BREATHE_UNDER_WATER).add(ModEntityTypes.CORAL_ENDERMAN.get().builtInRegistryHolder().key());
        builder(EntityTypeTags.ARTHROPOD).add(ModEntityTypes.SCARAB.get().builtInRegistryHolder().key());
        builder(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS).add(ModEntityTypes.SCARAB.get().builtInRegistryHolder().key());
    }
}

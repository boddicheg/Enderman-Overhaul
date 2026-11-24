package tech.alexnijjar.endermanoverhaul.datagen.provider.client;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;

public class ModItemModelProvider extends FabricModelProvider {
    public ModItemModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blocks) {}

    @Override
    public void generateItemModels(ItemModelGenerators items) {
        items.generateFlatItem(ModItems.CORRUPTED_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.SOUL_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.ANCIENT_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.BUBBLE_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.SUMMONER_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.ICY_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.CRIMSON_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.WARPED_PEARL.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.ENDERMAN_TOOTH.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.BADLANDS_HOOD.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.SAVANNAS_HOOD.get(), ModelTemplates.FLAT_ITEM);
        items.generateFlatItem(ModItems.SNOWY_HOOD.get(), ModelTemplates.FLAT_ITEM);
        ModItems.SPAWN_EGGS.getEntries().stream().map(RegistryEntry::get).forEach(item -> items.generateFlatItem(item, ModelTemplates.FLAT_ITEM));
    }
}

package tech.alexnijjar.endermanoverhaul.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import tech.alexnijjar.endermanoverhaul.datagen.provider.client.ModItemModelProvider;
import tech.alexnijjar.endermanoverhaul.datagen.provider.client.ModLangProvider;
import tech.alexnijjar.endermanoverhaul.datagen.provider.server.ModBlockTagProvider;
import tech.alexnijjar.endermanoverhaul.datagen.provider.server.ModEntityTypeTagProvider;
import tech.alexnijjar.endermanoverhaul.datagen.provider.server.ModItemTagProvider;
import tech.alexnijjar.endermanoverhaul.datagen.provider.server.ModLootTableProvider;

public final class EndermanOverhaulDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        var pack = generator.createPack();
        pack.addProvider(ModLangProvider::new);
        pack.addProvider(ModItemModelProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModEntityTypeTagProvider::new);
    }
}

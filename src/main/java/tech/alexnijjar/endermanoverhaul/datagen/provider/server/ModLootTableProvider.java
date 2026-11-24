package tech.alexnijjar.endermanoverhaul.datagen.provider.server;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModLootTableProvider extends SimpleFabricLootTableSubProvider {
    private final CompletableFuture<HolderLookup.Provider> registries;

    public ModLootTableProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, LootContextParamSets.ENTITY);
        this.registries = registries;
    }

    private HolderGetter<Enchantment> enchantments() {
        return registries.join().lookupOrThrow(Registries.ENCHANTMENT);
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        BiConsumer<EntityType<?>, LootTable.Builder> loot =
            (type, table) -> output.accept(type.getDefaultLootTable().orElseThrow(), table);

        loot.accept(ModEntityTypes.BADLANDS_ENDERMAN.get(), getDefaultEndermanLootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.TINY_SKULL.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.1f, 0.025f)))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.BADLANDS_HOOD.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.1f, 0.025f))
            )
        );

        loot.accept(ModEntityTypes.CAVE_ENDERMAN.get(), getDefaultEndermanLootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.COAL)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.RAW_IRON)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.EMERALD)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.1f, 0.025f))
            )
        );

        loot.accept(ModEntityTypes.CRIMSON_FOREST_ENDERMAN.get(), getEmptyLootTable(ModItems.CRIMSON_PEARL)
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.CRIMSON_FUNGUS)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            )
        );

        loot.accept(ModEntityTypes.DARK_OAK_ENDERMAN.get(), getDefaultEndermanLootTable());

        loot.accept(ModEntityTypes.DESERT_ENDERMAN.get(), getDefaultEndermanLootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.EMERALD)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.05f, 0.025f))
            )
        );

        loot.accept(ModEntityTypes.END_ENDERMAN.get(), getEmptyLootTable(ModItems.CORRUPTED_PEARL)
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.CHORUS_FRUIT)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.ENDERMAN_TOOTH.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 2)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.04f, 0.025f))
            )
        );

        loot.accept(ModEntityTypes.END_ISLANDS_ENDERMAN.get(), getDefaultEndermanLootTable(ModItems.ANCIENT_PEARL));

        loot.accept(ModEntityTypes.FLOWER_FIELDS_ENDERMAN.get(), getDefaultEndermanLootTable());

        loot.accept(ModEntityTypes.ICE_SPIKES_ENDERMAN.get(), getDefaultEndermanLootTable(ModItems.ICY_PEARL)
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.PACKED_ICE)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            )
        );

        loot.accept(ModEntityTypes.MUSHROOM_FIELDS_ENDERMAN.get(), getDefaultEndermanLootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.RED_MUSHROOM)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            )
        );

        loot.accept(ModEntityTypes.NETHER_WASTES_ENDERMAN.get(), getDefaultEndermanLootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.BONE)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            )
        );

        loot.accept(ModEntityTypes.CORAL_ENDERMAN.get(), getEmptyLootTable(ModItems.BUBBLE_PEARL));

        loot.accept(ModEntityTypes.SAVANNA_ENDERMAN.get(), getDefaultEndermanLootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.ACACIA_LOG)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 4)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.SAVANNAS_HOOD.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.1f, 0.025f))
            )
        );

        loot.accept(ModEntityTypes.SNOWY_ENDERMAN.get(), getDefaultEndermanLootTable(ModItems.ICY_PEARL)
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.SNOWBALL)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))))
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ModItems.SNOWY_HOOD.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1))))
                .when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(enchantments(), 0.1f, 0.025f))
            )
        );

        loot.accept(ModEntityTypes.SOULSAND_VALLEY_ENDERMAN.get(), getEmptyLootTable(ModItems.SOUL_PEARL)
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.BONE)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            )
        );

        loot.accept(ModEntityTypes.SWAMP_ENDERMAN.get(), getDefaultEndermanLootTable(ModItems.SUMMONER_PEARL));

        loot.accept(ModEntityTypes.WARPED_FOREST_ENDERMAN.get(), getEmptyLootTable(ModItems.WARPED_PEARL)
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.WARPED_FUNGUS)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 3)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            )
        );

        loot.accept(ModEntityTypes.WINDSWEPT_HILLS_ENDERMAN.get(), getDefaultEndermanLootTable(ModItems.SUMMONER_PEARL));
    }

    private LootTable.Builder getDefaultEndermanLootTable() {
        return LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            );
    }

    private LootTable.Builder getDefaultEndermanLootTable(Supplier<Item> additionalPearl) {
        return LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1))))
                .add(LootItem.lootTableItem(additionalPearl.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            );
    }

    private LootTable.Builder getEmptyLootTable(Supplier<Item> additionalPearl) {
        return LootTable.lootTable()
            .withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(additionalPearl.get())
                    .apply(SetItemCountFunction.setCount(ContextIntProviders.between(0, 1)))
                    .apply(EnchantedCountIncreaseFunction.lootingMultiplier(enchantments(), ContextFloatProviders.between(0, 1)))
                )
            );
    }
}

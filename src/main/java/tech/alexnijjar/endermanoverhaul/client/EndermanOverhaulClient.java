package tech.alexnijjar.endermanoverhaul.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.Level;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.client.config.EndermanOverhaulClientConfig;
import tech.alexnijjar.endermanoverhaul.client.gui.FlashOverlay;
import tech.alexnijjar.endermanoverhaul.client.particles.EndermanParticle;
import tech.alexnijjar.endermanoverhaul.client.renderer.EndIslandsEndermanRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.EnderBulletRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.MushroomFieldsEndermanRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.ReplacedEndermanRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.BaseEndermanEntityRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.summons.ScarabRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.summons.SpiritRenderer;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;
import tech.alexnijjar.endermanoverhaul.common.registry.ModParticleTypes;

public class EndermanOverhaulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EndermanOverhaul.CONFIGURATOR.register(EndermanOverhaulClientConfig.class);
        registerEntityRenderers();
        registerParticles();
        HudElementRegistry.addLast(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "flash"),
            (graphics, deltaTracker) -> FlashOverlay.render(graphics));
    }

    private static void registerParticles() {
        var registry = ParticleProviderRegistry.getInstance();
        registry.register(ModParticleTypes.DUST.get(), spriteSet -> new EndermanParticle.Provider(spriteSet, 0.7f, 0.7f, 0.7f));
        registry.register(ModParticleTypes.SNOW.get(), EndermanParticle.Provider::new);
        registry.register(ModParticleTypes.SAND.get(), spriteSet -> new EndermanParticle.Provider(spriteSet, 0.86f, 0.83f, 0.63f));
        registry.register(ModParticleTypes.SOUL_FIRE_FLAME.get(), EndermanParticle.Provider::new);
        registry.register(ModParticleTypes.BUBBLE.get(), EndermanParticle.Provider::new);
        registry.register(ModParticleTypes.FRIENDERMAN.get(), spriteSet -> new EndermanParticle.Provider(spriteSet, 0.07f, 0.93f, 0.84f));
    }

    private static void registerEntityRenderers() {
        if (EndermanOverhaulClientConfig.replaceDefaultEnderman) {
            EntityRendererRegistry.register(EntityTypes.ENDERMAN, ReplacedEndermanRenderer::new);
        }
        EntityRendererRegistry.register(ModEntityTypes.BADLANDS_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.BADLANDS_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.CAVE_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.CAVE_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.CRIMSON_FOREST_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.CRIMSON_FOREST_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.DARK_OAK_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.DARK_OAK_ENDERMAN.get(), BaseEndermanEntityRenderer.DARK_OAK_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.DESERT_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.DESERT_ENDERMAN.get(), BaseEndermanEntityRenderer.DESERT_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.END_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.END_ENDERMAN.get(), BaseEndermanEntityRenderer.END_ANIMATION, false));
        EntityRendererRegistry.register(ModEntityTypes.END_ISLANDS_ENDERMAN.get(), EndIslandsEndermanRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.FLOWER_FIELDS_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.FLOWER_FIELDS_ENDERMAN.get(), BaseEndermanEntityRenderer.FLOWER_FIELDS_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.ICE_SPIKES_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.ICE_SPIKES_ENDERMAN.get(), BaseEndermanEntityRenderer.ICE_SPIKES_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.MUSHROOM_FIELDS_ENDERMAN.get(), MushroomFieldsEndermanRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.NETHER_WASTES_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.NETHER_WASTES_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.CORAL_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.CORAL_ENDERMAN.get(), BaseEndermanEntityRenderer.CORAL_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.SAVANNA_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.SAVANNA_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.SNOWY_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.SNOWY_ENDERMAN.get(), BaseEndermanEntityRenderer.SNOWY_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.SOULSAND_VALLEY_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.SOULSAND_VALLEY_ENDERMAN.get(), BaseEndermanEntityRenderer.SOULSAND_VALLEY_ANIMATION, false));
        EntityRendererRegistry.register(ModEntityTypes.SWAMP_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.SWAMP_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.WARPED_FOREST_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.WARPED_FOREST_ENDERMAN.get(), BaseEndermanEntityRenderer.WARPED_FOREST_ANIMATION));
        EntityRendererRegistry.register(ModEntityTypes.WINDSWEPT_HILLS_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.WINDSWEPT_HILLS_ENDERMAN.get(), BaseEndermanEntityRenderer.WINDSWEPT_HILLS_ANIMATION));

        // Pets
        EntityRendererRegistry.register(ModEntityTypes.PET_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.PET_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.HAMMERHEAD_PET_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.HAMMERHEAD_PET_ENDERMAN.get()));
        EntityRendererRegistry.register(ModEntityTypes.AXOLOTL_PET_ENDERMAN.get(), c -> new BaseEndermanEntityRenderer<>(c, ModEntityTypes.AXOLOTL_PET_ENDERMAN.get()));

        // Summons
        EntityRendererRegistry.register(ModEntityTypes.SCARAB.get(), ScarabRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SPIRIT.get(), SpiritRenderer::new);

        // Projectiles
        EntityRendererRegistry.register(ModEntityTypes.ENDER_BULLET.get(), EnderBulletRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CORRUPTED_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ANCIENT_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SOUL_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.BUBBLE_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.SUMMONER_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.ICY_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.CRIMSON_PEARL.get(), ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityTypes.WARPED_PEARL.get(), ThrownItemRenderer::new);
    }

    public static Level getLevel() {
        return Minecraft.getInstance().level;
    }
}

package tech.alexnijjar.endermanoverhaul.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.BaseEndermanEntityRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.BaseEndermanModel;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.CustomEnderEyesLayer;
import tech.alexnijjar.endermanoverhaul.common.entities.EndIslandsEnderman;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;

public class EndIslandsEndermanRenderer extends BaseEndermanEntityRenderer<EndIslandsEnderman> {

    public EndIslandsEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BaseEndermanModel<>(
            BuiltInRegistries.ENTITY_TYPE.getKey(ModEntityTypes.END_ISLANDS_ENDERMAN.get()),
            true,
            getTexture(ModEntityTypes.END_ISLANDS_ENDERMAN.get()),
            END_ISLANDS_ANIMATION));

        withRenderLayer(new CustomEnderEyesLayer<>(this, Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "textures/entity/end_islands/end_islands_enderman_glow.png")));
        withRenderLayer(new CustomEnderEyesLayer<>(this, Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "textures/entity/end_islands/end_islands_enderman_glow_2.png")));
        withRenderLayer(new CustomEnderEyesLayer<>(this, Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "textures/entity/end_islands/end_islands_enderman_glow_3.png")));
    }

    @Override
    protected boolean shouldShake(EndIslandsEnderman entity) {
        return !entity.isPossessing() && super.shouldShake(entity);
    }
}

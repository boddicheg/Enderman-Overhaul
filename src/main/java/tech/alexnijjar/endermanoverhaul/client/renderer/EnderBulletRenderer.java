package tech.alexnijjar.endermanoverhaul.client.renderer;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import tech.alexnijjar.endermanoverhaul.common.entities.projectiles.EnderBullet;
import tech.alexnijjar.endermanoverhaul.common.registry.ModEntityTypes;

public class EnderBulletRenderer extends GeoEntityRenderer<EnderBullet, EntityRenderState> {

    public EnderBulletRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(BuiltInRegistries.ENTITY_TYPE.getKey(ModEntityTypes.ENDER_BULLET.get())));
    }

    @Override
    public RenderType getRenderType(EntityRenderState state, Identifier texture) {
        return RenderTypes.entityTranslucentEmissive(texture);
    }
}

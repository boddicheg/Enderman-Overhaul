package tech.alexnijjar.endermanoverhaul.client.renderer.summons;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.entities.summons.Scarab;

public class ScarabRenderer extends GeoEntityRenderer<Scarab, LivingEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "scarab/scarab");

    public ScarabRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<Scarab>(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "scarab"))
            .withAltTexture(TEXTURE)
            .withAltAnimations(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "scarab")));
    }
}

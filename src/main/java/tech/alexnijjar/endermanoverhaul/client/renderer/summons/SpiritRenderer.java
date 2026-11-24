package tech.alexnijjar.endermanoverhaul.client.renderer.summons;

import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.CustomEnderEyesLayer;
import tech.alexnijjar.endermanoverhaul.common.entities.summons.Spirit;

public class SpiritRenderer extends GeoEntityRenderer<Spirit, LivingEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "spirit/spirit");
    public static final Identifier GLOW = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "textures/entity/spirit/spirit_glow.png");

    public SpiritRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<Spirit>(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "spirit"))
            .withAltTexture(TEXTURE)
            .withAltAnimations(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "spirit")));
        withRenderLayer(new CustomEnderEyesLayer<>(this, GLOW));
    }
}

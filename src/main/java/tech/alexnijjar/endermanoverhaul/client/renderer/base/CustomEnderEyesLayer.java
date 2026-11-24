package tech.alexnijjar.endermanoverhaul.client.renderer.base;

import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.GeoRenderer;
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer;
import net.minecraft.resources.Identifier;

public final class CustomEnderEyesLayer<T extends GeoAnimatable, O, R extends GeoRenderState>
    extends AutoGlowingGeoLayer<T, O, R> {
    private final Identifier texture;

    public CustomEnderEyesLayer(GeoRenderer<T, O, R> renderer, Identifier texture) {
        super(renderer);
        this.texture = texture;
    }

    @Override
    protected Identifier getTextureResource(R renderState) {
        return texture;
    }
}

package tech.alexnijjar.endermanoverhaul.client.renderer.base;

import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.model.DefaultedEntityGeoModel;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.RenderPassInfo;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class BaseEndermanModel<T extends GeoAnimatable> extends DefaultedEntityGeoModel<T> {
    private final boolean turnsHead;

    public BaseEndermanModel(Identifier assetSubpath, boolean turnsHead, Identifier texture, Identifier animation) {
        super(assetSubpath);
        withAltTexture(texture);
        withAltAnimations(animation);
        this.turnsHead = turnsHead;
    }

    public void applyHeadRotation(RenderPassInfo<LivingEntityRenderState> pass, BoneSnapshots snapshots) {
        if (!turnsHead) return;

        pass.model().getBone("head").ifPresent(head -> {
            var children = head.children();
            if (children.length == 0) return;

            var rotation = snapshots.get(children[0]);
            rotation.setRotX(pass.renderState().xRot * Mth.DEG_TO_RAD);
            rotation.setRotY(pass.renderState().yRot * Mth.DEG_TO_RAD);
        });
    }
}

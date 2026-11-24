package tech.alexnijjar.endermanoverhaul.client.renderer.items;

import com.geckolib.model.DefaultedItemGeoModel;
import com.geckolib.renderer.GeoItemRenderer;
import com.geckolib.renderer.base.GeoRenderState;
import com.geckolib.renderer.base.RenderPassInfo;
import net.minecraft.core.registries.BuiltInRegistries;
import tech.alexnijjar.endermanoverhaul.common.items.tools.CorruptedShieldItem;
import tech.alexnijjar.endermanoverhaul.common.registry.ModItems;

public class CorruptedShieldRenderer extends GeoItemRenderer<CorruptedShieldItem> {
    public CorruptedShieldRenderer() {
        super(new DefaultedItemGeoModel<>(BuiltInRegistries.ITEM.getKey(ModItems.CORRUPTED_SHIELD.get())));
    }

    @Override
    public void adjustRenderPose(RenderPassInfo<GeoRenderState> pass) {
        super.adjustRenderPose(pass);
        pass.poseStack().translate(0.0, -0.5, 0.0);
    }
}

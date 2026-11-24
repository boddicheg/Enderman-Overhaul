package tech.alexnijjar.endermanoverhaul.client.renderer.items;

import com.geckolib.model.DefaultedItemGeoModel;
import com.geckolib.renderer.GeoArmorRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.items.HoodItem;

import java.util.List;

public class HoodRenderer extends GeoArmorRenderer<HoodItem, HumanoidRenderState> {
    public HoodRenderer(String name) {
        super(new DefaultedItemGeoModel<HoodItem>(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, name))
            .withAltTexture(Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "armor/" + name)));
    }

    @Override
    public List<ArmorSegment> getSegmentsForSlot(HumanoidRenderState state, EquipmentSlot slot) {
        return slot == EquipmentSlot.CHEST
            ? List.of(ArmorSegment.HEAD, ArmorSegment.CHEST, ArmorSegment.LEFT_ARM, ArmorSegment.RIGHT_ARM)
            : super.getSegmentsForSlot(state, slot);
    }
}

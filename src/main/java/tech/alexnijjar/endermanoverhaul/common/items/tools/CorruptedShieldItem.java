package tech.alexnijjar.endermanoverhaul.common.items.tools;

import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.renderer.GeoItemRenderer;
import com.geckolib.util.GeckoLibUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import tech.alexnijjar.endermanoverhaul.client.renderer.items.CorruptedShieldRenderer;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantComponents;

import java.util.function.Consumer;

public class CorruptedShieldItem extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public CorruptedShieldItem(Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.accept(ConstantComponents.CORRUPTED_SHIELD_TOOLTIP);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final GeoItemRenderer<?> renderer = new CorruptedShieldRenderer();

            @Override
            public GeoItemRenderer<?> getGeoItemRenderer() {
                return this.renderer;
            }
        });
    }
}

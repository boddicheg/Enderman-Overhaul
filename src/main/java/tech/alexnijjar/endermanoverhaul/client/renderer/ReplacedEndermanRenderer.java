package tech.alexnijjar.endermanoverhaul.client.renderer;

import com.geckolib.renderer.GeoReplacedEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.RenderPassInfo;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Enderman;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.BaseEndermanEntityRenderer;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.BaseEndermanModel;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.CustomCarriedBlockLayer;
import tech.alexnijjar.endermanoverhaul.client.renderer.base.CustomEnderEyesLayer;
import tech.alexnijjar.endermanoverhaul.common.entities.ReplacedEnderman;

public class ReplacedEndermanRenderer extends GeoReplacedEntityRenderer<ReplacedEnderman, Enderman, LivingEntityRenderState> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "default/default_enderman");
    public static final Identifier GLOW = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "textures/entity/default/default_enderman_glow.png");

    public ReplacedEndermanRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BaseEndermanModel<>(
                Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "default_enderman"),
                true,
                TEXTURE,
                BaseEndermanEntityRenderer.ANIMATION),
            new ReplacedEnderman());

        withRenderLayer(new CustomEnderEyesLayer<>(this, GLOW));
        withRenderLayer(new CustomCarriedBlockLayer<>(renderManager, this, (animatable, entity) -> entity));
    }

    @Override
    public void addRenderData(ReplacedEnderman animatable, Enderman entity, LivingEntityRenderState state, float partialTick) {
        state.addGeckolibData(ReplacedEnderman.ANIMATION_DATA, new ReplacedEnderman.AnimationData(
            entity.isCreepy(), entity.getCarriedBlock() != null, entity.getSwingAnimation(partialTick) != 0));
        if (entity.isCreepy()) {
            var random = entity.getRandom();
            state.addGeckolibData(BaseEndermanEntityRenderer.SHAKE_OFFSET,
                new Vec3(random.nextGaussian() * 0.02, 0, random.nextGaussian() * 0.02));
        }
    }

    @Override
    public @NotNull Vec3 getRenderOffset(LivingEntityRenderState state) {
        return super.getRenderOffset(state).add(state.getOrDefaultGeckolibData(BaseEndermanEntityRenderer.SHAKE_OFFSET, Vec3.ZERO));
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<LivingEntityRenderState> pass, BoneSnapshots snapshots) {
        ((BaseEndermanModel<ReplacedEnderman>) getGeoModel()).applyHeadRotation(pass, snapshots);
    }
}

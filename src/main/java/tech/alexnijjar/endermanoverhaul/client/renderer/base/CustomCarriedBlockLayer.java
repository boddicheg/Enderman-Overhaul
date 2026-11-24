package tech.alexnijjar.endermanoverhaul.client.renderer.base;

import com.geckolib.animatable.GeoAnimatable;
import com.geckolib.cache.model.GeoBone;
import com.geckolib.constant.dataticket.DataTicket;
import com.geckolib.renderer.base.GeoRenderer;
import com.geckolib.renderer.base.PerBoneRender;
import com.geckolib.renderer.base.RenderPassInfo;
import com.geckolib.renderer.layer.GeoRenderLayer;
import com.google.common.reflect.TypeToken;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.phys.Vec3;
import tech.alexnijjar.endermanoverhaul.common.entities.base.BaseEnderman;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public final class CustomCarriedBlockLayer<T extends GeoAnimatable, O>
    extends GeoRenderLayer<T, O, LivingEntityRenderState> {
    private static final DataTicket<CarriedBlock> CARRIED_BLOCK = DataTicket.create("endermanoverhaul_carried_block", new TypeToken<>() {});
    private final BlockModelResolver blockModels;
    private final BiFunction<T, O, EnderMan> entity;

    public CustomCarriedBlockLayer(EntityRendererProvider.Context context, GeoRenderer<T, O, LivingEntityRenderState> renderer,
                                   BiFunction<T, O, EnderMan> entity) {
        super(renderer);
        this.blockModels = context.getBlockModelResolver();
        this.entity = entity;
    }

    @Override
    public void addRenderData(T animatable, O relatedObject, LivingEntityRenderState renderState, float partialTick) {
        EnderMan enderman = entity.apply(animatable, relatedObject);
        var block = enderman.getCarriedBlock();
        if (block == null) return;

        var model = new BlockModelRenderState();
        blockModels.update(model, block, BlockDisplayContext.create());
        var offset = enderman instanceof BaseEnderman base ? base.getHeldBlockOffset() : Vec3.ZERO;
        renderState.addGeckolibData(CARRIED_BLOCK, new CarriedBlock(model, offset));
    }

    @Override
    public void addPerBoneRender(RenderPassInfo<LivingEntityRenderState> pass,
                                 BiConsumer<GeoBone, PerBoneRender<LivingEntityRenderState>> renders) {
        var block = pass.renderState().getGeckolibData(CARRIED_BLOCK);
        if (block == null || block.model().isEmpty()) return;

        pass.model().getBone("left_arm").ifPresent(arm -> renders.accept(arm, (info, bone, queue) -> {
            var pose = info.poseStack();
            pose.pushPose();
            pose.translate(block.offset());
            pose.translate(-0.25, -1.05, -0.25);
            pose.mulPose(Axis.XP.rotationDegrees(-8.65f));
            pose.mulPose(Axis.YP.rotationDegrees(45f));
            pose.scale(0.5f, 0.5f, 0.5f);
            block.model().submit(pose, queue, info.packedLight(), OverlayTexture.NO_OVERLAY, info.renderState().outlineColor);
            pose.popPose();
        }));
    }

    private record CarriedBlock(BlockModelRenderState model, Vec3 offset) {}
}

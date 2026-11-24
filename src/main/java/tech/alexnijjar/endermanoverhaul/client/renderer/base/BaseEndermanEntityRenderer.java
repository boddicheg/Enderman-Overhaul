package tech.alexnijjar.endermanoverhaul.client.renderer.base;

import com.geckolib.constant.dataticket.DataTicket;
import com.geckolib.renderer.GeoEntityRenderer;
import com.geckolib.renderer.base.BoneSnapshots;
import com.geckolib.renderer.base.RenderPassInfo;
import com.google.common.reflect.TypeToken;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import tech.alexnijjar.endermanoverhaul.EndermanOverhaul;
import tech.alexnijjar.endermanoverhaul.common.entities.base.BaseEnderman;

public class BaseEndermanEntityRenderer<T extends BaseEnderman> extends GeoEntityRenderer<T, LivingEntityRenderState> {
    public static final DataTicket<Vec3> SHAKE_OFFSET = DataTicket.create("endermanoverhaul_shake_offset", new TypeToken<>() {});
    public static final Identifier ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "enderman");
    public static final Identifier ICE_SPIKES_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "ice_spikes_enderman");
    public static final Identifier SNOWY_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "snowy_enderman");
    public static final Identifier DESERT_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "desert_enderman");
    public static final Identifier SOULSAND_VALLEY_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "soulsand_valley_enderman");
    public static final Identifier END_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "end_enderman");
    public static final Identifier FLOWER_FIELDS_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "flower_fields_enderman");
    public static final Identifier END_ISLANDS_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "end_islands_enderman");
    public static final Identifier WARPED_FOREST_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "warped_forest_enderman");
    public static final Identifier CORAL_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "coral_enderman");
    public static final Identifier DARK_OAK_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "dark_oak_enderman");
    public static final Identifier WINDSWEPT_HILLS_ANIMATION = Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "windswept_hills_enderman");

    public BaseEndermanEntityRenderer(EntityRendererProvider.Context renderManager, EntityType<?> enderman) {
        this(renderManager, enderman, ANIMATION);
    }

    public BaseEndermanEntityRenderer(EntityRendererProvider.Context renderManager, EntityType<?> enderman, Identifier animation) {
        this(renderManager, enderman, animation, true);
    }

    public BaseEndermanEntityRenderer(EntityRendererProvider.Context renderManager, EntityType<?> enderman, Identifier animation, boolean turnsHead) {
        this(renderManager,
            BuiltInRegistries.ENTITY_TYPE.getKey(enderman),
            getTexture(enderman),
            animation,
            getGlowTexture(enderman),
            turnsHead);
    }

    public BaseEndermanEntityRenderer(EntityRendererProvider.Context renderManager, Identifier assetPath, Identifier texture, Identifier animation, Identifier glow, boolean turnsHead) {
        super(renderManager, new BaseEndermanModel<>(assetPath, turnsHead, texture, animation));
        if (glow != null) {
            withRenderLayer(new CustomEnderEyesLayer<>(this, glow));
        }
        withRenderLayer(new CustomCarriedBlockLayer<>(renderManager, this, (entity, unused) -> entity));
    }

    public BaseEndermanEntityRenderer(EntityRendererProvider.Context renderManager, BaseEndermanModel<T> model) {
        super(renderManager, model);
    }

    @Override
    public void addRenderData(T entity, Void unused, LivingEntityRenderState state, float partialTick) {
        if (shouldShake(entity)) {
            var random = entity.getRandom();
            state.addGeckolibData(SHAKE_OFFSET, new Vec3(random.nextGaussian() * 0.02, 0, random.nextGaussian() * 0.02));
        }
    }

    protected boolean shouldShake(T entity) {
        return entity.isCreepy() && entity.canShake();
    }

    @Override
    public @NotNull Vec3 getRenderOffset(LivingEntityRenderState state) {
        return super.getRenderOffset(state).add(state.getOrDefaultGeckolibData(SHAKE_OFFSET, Vec3.ZERO));
    }

    @Override
    public void adjustModelBonesForRender(RenderPassInfo<LivingEntityRenderState> pass, BoneSnapshots snapshots) {
        ((BaseEndermanModel<T>) getGeoModel()).applyHeadRotation(pass, snapshots);
    }

    public static Identifier getTexture(EntityType<?> enderman) {
        String name = BuiltInRegistries.ENTITY_TYPE.getKey(enderman).getPath();
        return Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "%s/%s".formatted(name.replace("_enderman", ""), name));
    }

    public static Identifier getGlowTexture(EntityType<?> enderman) {
        String name = BuiltInRegistries.ENTITY_TYPE.getKey(enderman).getPath();
        return Identifier.fromNamespaceAndPath(EndermanOverhaul.MOD_ID, "textures/entity/%s/%s_glow.png".formatted(name.replace("_enderman", ""), name));
    }
}

package tech.alexnijjar.endermanoverhaul.common.entities;

import com.geckolib.animatable.GeoReplacedEntity;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.animation.AnimationController;
import com.geckolib.animation.object.PlayState;
import com.geckolib.constant.dataticket.DataTicket;
import com.geckolib.util.GeckoLibUtil;
import com.google.common.reflect.TypeToken;
import tech.alexnijjar.endermanoverhaul.common.constants.ConstantAnimations;

public class ReplacedEnderman implements GeoReplacedEntity {
    public static final DataTicket<AnimationData> ANIMATION_DATA = DataTicket.create("endermanoverhaul_vanilla_animation", new TypeToken<>() {});

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>("main", 0, state -> {
            AnimationData enderman = state.getData(ANIMATION_DATA);
            if (enderman == null) return PlayState.STOP;

            if (state.isMoving()) {
                state.setAnimation(enderman.creepy() ?
                    ConstantAnimations.RUN :
                    ConstantAnimations.WALK);
                state.setControllerSpeed(2);
            } else {
                state.setAnimation(ConstantAnimations.IDLE);
                state.setControllerSpeed(1);
            }
            return PlayState.CONTINUE;
        }));

        controllers.add(new AnimationController<>("creepy_controller", 5, state -> {
            AnimationData enderman = state.getData(ANIMATION_DATA);
            if (enderman == null) return PlayState.STOP;
            if (!enderman.creepy()) return PlayState.STOP;
            state.setAnimation(ConstantAnimations.ANGRY);
            return PlayState.CONTINUE;
        }));

        controllers.add(new AnimationController<>("hold_controller", 5, state -> {
            AnimationData enderman = state.getData(ANIMATION_DATA);
            if (enderman == null) return PlayState.STOP;
            if (!enderman.carryingBlock()) return PlayState.STOP;
            state.setAnimation(ConstantAnimations.HOLDING);
            return PlayState.CONTINUE;
        }));

        controllers.add(new AnimationController<>("attack_controller", 5, state -> {
            AnimationData enderman = state.getData(ANIMATION_DATA);
            if (enderman == null) return PlayState.STOP;
            if (!enderman.attacking()) return PlayState.STOP;
            state.setAnimation(ConstantAnimations.ATTACK);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public record AnimationData(boolean creepy, boolean carryingBlock, boolean attacking) {}
}

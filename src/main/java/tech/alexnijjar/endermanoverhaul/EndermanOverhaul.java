package tech.alexnijjar.endermanoverhaul;

import com.teamresourceful.resourcefulconfig.api.loader.Configurator;
import net.fabricmc.api.ModInitializer;
import tech.alexnijjar.endermanoverhaul.common.config.EndermanOverhaulConfig;
import tech.alexnijjar.endermanoverhaul.common.network.NetworkHandler;
import tech.alexnijjar.endermanoverhaul.common.registry.*;

public class EndermanOverhaul implements ModInitializer {

    public static final String MOD_ID = "endermanoverhaul";
    public static final Configurator CONFIGURATOR = new Configurator(MOD_ID);

    @Override
    public void onInitialize() {
        CONFIGURATOR.register(EndermanOverhaulConfig.class);

        NetworkHandler.init();
        ModDataComponents.DATA_COMPONENT_TYPES.init();
        ModBlocks.BLOCKS.init();
        ModEntityTypes.ENTITY_TYPES.init();
        ModItems.ITEMS.init();
        ModItems.TABS.init();
        ModParticleTypes.PARTICLE_TYPES.init();
        ModSoundEvents.SOUND_EVENTS.init();
        ModEntityTypes.registerAttributes();
        ModEntityTypes.registerSpawnPlacements();
        EndermanSpawns.register();
    }
}

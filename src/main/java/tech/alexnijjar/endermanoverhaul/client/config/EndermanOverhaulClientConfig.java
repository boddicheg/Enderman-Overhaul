package tech.alexnijjar.endermanoverhaul.client.config;

import com.teamresourceful.resourcefulconfig.api.annotations.Comment;
import com.teamresourceful.resourcefulconfig.api.annotations.Config;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigEntry;
import com.teamresourceful.resourcefulconfig.api.annotations.ConfigInfo;
import tech.alexnijjar.endermanoverhaul.client.config.info.EndermanOverhaulConfigInfo;

@Config("endermanoverhaul-client")
@ConfigInfo.Provider(EndermanOverhaulConfigInfo.class)
public final class EndermanOverhaulClientConfig {

    @ConfigEntry(
        id = "replaceDefaultEnderman",
        translation = "config.endermanoverhaul.replaceDefaultEnderman"
    )
    @Comment("Replaces the default Enderman model with a better-looking animated one.")
    public static boolean replaceDefaultEnderman = true;

    @ConfigEntry(
        id = "flashScreen",
        translation = "config.endermanoverhaul.flashScreen"
    )
    @Comment("Flashes the screen when getting teleported by an End Enderman.")
    public static boolean flashScreen = true;
}

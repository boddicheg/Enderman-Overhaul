package tech.alexnijjar.endermanoverhaul.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import tech.alexnijjar.endermanoverhaul.client.config.EndermanOverhaulClientConfig;
import tech.alexnijjar.endermanoverhaul.common.entities.EndEnderman;

public class FlashOverlay {
    public static boolean shouldFlash;

    public static void render(GuiGraphicsExtractor graphics) {
        if (!shouldFlash) return;
        if (!EndermanOverhaulClientConfig.flashScreen) return;
        var player = Minecraft.getInstance().player;
        if (player == null) return;
        int hurtTime = player.hurtTime;
        if (hurtTime < 5) return;
        var damageSource = player.getLastDamageSource();
        if (damageSource == null) return;
        var entity = damageSource.getEntity();
        if (!(entity instanceof EndEnderman)) return;
        int alpha = Math.round((hurtTime - 5) * 0.05f * 255);

        graphics.fill(0, 0, graphics.guiWidth(), graphics.guiHeight(), (alpha << 24) | 0xFFFFFF);
        if (hurtTime == 5) shouldFlash = false;
    }
}

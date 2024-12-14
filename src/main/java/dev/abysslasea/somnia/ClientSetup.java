package dev.abysslasea.somnia;

import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;

public final class ClientSetup {

    static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("fatigue_overlay", ClientSleepHandler.INSTANCE::renderGuiTextOverlay);
        event.registerBelowAll("fatigue_hub", ClientSleepHandler.HUB_RENDER::renderGuiHudOverlay);
    }
    private ClientSetup() {}
}

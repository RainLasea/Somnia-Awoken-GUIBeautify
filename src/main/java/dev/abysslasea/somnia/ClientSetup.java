package dev.abysslasea.somnia;

import dev.abysslasea.somnia.gui.PlayerFatigueHub;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import dev.abysslasea.somnia.ClientSleepHandler;

public final class ClientSetup {

    static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerBelowAll("fatigue_overlay", ClientSleepHandler.INSTANCE::renderGuiOverlay);
        event.registerBelowAll("fatigue_hub", PlayerFatigueHub.HUD_FATIGUE);
    }
    private ClientSetup() {}
}

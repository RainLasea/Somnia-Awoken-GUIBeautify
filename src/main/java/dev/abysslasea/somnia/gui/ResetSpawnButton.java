package dev.abysslasea.somnia.gui;

import dev.abysslasea.somnia.capability.CapabilityFatigue;
import dev.abysslasea.somnia.network.SomniaNetwork;
import dev.abysslasea.somnia.network.server.ResetSpawnPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class ResetSpawnButton extends AbstractButton {
    public boolean resetSpawn = true;

    public ResetSpawnButton(int xIn, int yIn, int widthIn, int heightIn) {
        super(xIn, yIn, widthIn, heightIn, Component.translatable("somnia.gui.reset_spawn"));
    }

    @Override
    public void onPress() {
        this.resetSpawn = !this.resetSpawn;
        setMessage(Component.translatable(this.resetSpawn ? "somnia.gui.reset_spawn" : "somnia.gui.no_reset_spawn"));

        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.getCapability(CapabilityFatigue.INSTANCE)
                .ifPresent(props -> SomniaNetwork.INSTANCE.sendToServer(new ResetSpawnPacket(this.resetSpawn)));
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.HINT, getMessage());
    }
}

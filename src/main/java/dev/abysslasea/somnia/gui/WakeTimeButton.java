package dev.abysslasea.somnia.gui;

import dev.abysslasea.somnia.capability.CapabilityFatigue;
import dev.abysslasea.somnia.network.SomniaNetwork;
import dev.abysslasea.somnia.network.server.ActivateBlockPacket;
import dev.abysslasea.somnia.network.server.WakeTimeUpdatePacket;
import dev.abysslasea.somnia.util.SomniaUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class WakeTimeButton extends Button {
    private final Component hoverMessage;
    private final Component buttonMessage;

    public WakeTimeButton(int x, int y, int widthIn, int heightIn, Component message, int wakeTime) {
        super(x, y, widthIn, heightIn, message, button -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level == null) return;

            long targetWakeTime = SomniaUtil.calculateWakeTime(mc.level, wakeTime);
            SomniaNetwork.INSTANCE.sendToServer(new WakeTimeUpdatePacket(targetWakeTime));
            mc.player.getCapability(CapabilityFatigue.INSTANCE)
                .ifPresent(props -> props.setWakeTime(targetWakeTime));

            HitResult mouseOver = mc.hitResult;
            if (mouseOver instanceof BlockHitResult blockHit) {
                Vec3 hitVec = mouseOver.getLocation();
                ActivateBlockPacket packet = new ActivateBlockPacket(blockHit.getBlockPos(), blockHit.getDirection(), (float) hitVec.x, (float) hitVec.y, (float) hitVec.z);
                SomniaNetwork.INSTANCE.sendToServer(packet);
            }

            mc.setScreen(null);
        }, DEFAULT_NARRATION);

        this.buttonMessage = message;
        this.hoverMessage = Component.literal(SomniaUtil.timeStringForGameTime(wakeTime));
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
        this.setMessage(this.isHovered ? this.hoverMessage : this.buttonMessage);
    }
}

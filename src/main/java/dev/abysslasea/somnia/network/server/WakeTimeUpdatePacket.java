package dev.abysslasea.somnia.network.server;

import dev.abysslasea.somnia.capability.CapabilityFatigue;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WakeTimeUpdatePacket {
    private final long wakeTime;

    public WakeTimeUpdatePacket(long wakeTime) {
        this.wakeTime = wakeTime;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeLong(this.wakeTime);
    }

    public static WakeTimeUpdatePacket decode(FriendlyByteBuf buf) {
        long wakeTime = buf.readLong();
        return new WakeTimeUpdatePacket(wakeTime);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().getSender().getCapability(CapabilityFatigue.INSTANCE).ifPresent(props -> props.setWakeTime(this.wakeTime));
    }
}

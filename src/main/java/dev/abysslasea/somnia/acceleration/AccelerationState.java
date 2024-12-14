package dev.abysslasea.somnia.acceleration;

import dev.abysslasea.somnia.SomniaCommand;
import dev.abysslasea.somnia.capability.CapabilityFatigue;
import dev.abysslasea.somnia.capability.Fatigue;
import dev.abysslasea.somnia.compat.DarkUtilsCompat;
import dev.abysslasea.somnia.util.SomniaUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public enum AccelerationState {
    UNAVAILABLE,
    INACTIVE,
    WAITING,
    SIMULATING;

    public static AccelerationState forLevel(ServerLevel level) {
        if (!SomniaUtil.isValidSleepTime(level)) return UNAVAILABLE;

        List<ServerPlayer> players = level.players();
        if (!players.isEmpty()) {
            boolean anySleeping = false;
            boolean allSleeping = true;
            int acceleratedSleep = 0;
            int normalSleep = 0;

            for (ServerPlayer player : players) {
                boolean sleeping = player.isSleeping() || SomniaCommand.OVERRIDES.contains(player.getUUID());
                anySleeping |= sleeping;
                allSleeping &= sleeping;

                if (shouldSleepNormally(player)) normalSleep++;
                else acceleratedSleep++;
            }

            if (allSleeping) {
                if (acceleratedSleep >= normalSleep) {
                    return SIMULATING;
                }
            }
            else if (anySleeping) {
                return WAITING;
            }
        }

        return INACTIVE;
    }

    private static boolean shouldSleepNormally(Player player) {
        boolean sleepNormally = player.getCapability(CapabilityFatigue.INSTANCE)
            .map(Fatigue::shouldSleepNormally)
            .orElse(false);
        return sleepNormally || DarkUtilsCompat.hasSleepCharm(player);
    }
}

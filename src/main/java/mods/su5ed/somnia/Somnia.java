package mods.su5ed.somnia;

import mods.su5ed.somnia.api.capability.CapabilityFatigue;
import mods.su5ed.somnia.common.ForgeEventHandler;
import mods.su5ed.somnia.common.PlayerSleepTickHandler;
import mods.su5ed.somnia.common.config.ConfigHolder;
import mods.su5ed.somnia.common.network.NetworkHandler;
import mods.su5ed.somnia.server.ServerTickHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@Mod("somnia")
public class Somnia {
    public static final String MODID = "somnia";
    public static final Logger LOGGER = LogManager.getLogger();
    public static Somnia instance;

    public final List<ServerTickHandler> tickHandlers = new ArrayList<>();
    public final Set<UUID> ignoreList = new HashSet<>();
    public static ForgeEventHandler forgeEventHandler;

    public Somnia() {
        instance = this;

        ModLoadingContext context = ModLoadingContext.get();
        context.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(forgeEventHandler = new ForgeEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerSleepTickHandler());
    }

    private void setup(final FMLCommonSetupEvent event) {
        CapabilityFatigue.register();
        NetworkHandler.registerMessages();
    }
}
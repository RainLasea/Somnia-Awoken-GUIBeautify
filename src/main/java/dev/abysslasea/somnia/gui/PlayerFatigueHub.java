package dev.abysslasea.somnia.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.abysslasea.somnia.SomniaAwoken;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class PlayerFatigueHub{
    //"'textures/gui/side_effect_' + desc +'.png'"
    private static final ResourceLocation SIDE_EFFECT_0 = new ResourceLocation(SomniaAwoken.MODID, "textures/gui/side_effect_0.png");
    public static final IGuiOverlay HUD_FATIGUE =((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionColorTexLightmapShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,SIDE_EFFECT_0);

        for(int i = 0;i < 10;i++){
            guiGraphics.blit(SIDE_EFFECT_0, x + 98, y - 22, 0, 0, 22, 22, 22, 22);
        }
    });
}

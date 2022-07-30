/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.aresclient.discordrpc.DiscordApp
 *  com.aresclient.utils.config.ConfigManager
 *  com.aresclient.visuals.GuiAresMenu
 *  com.aresclient.visuals.GuiCosmetics
 *  com.aresclient.visuals.GuiModSettings
 *  com.aresclient.visuals.GuiTimechanger
 *  com.aresclient.visuals.hud.ui.GuiHud
 *  com.aresclient.visuals.utils.items.AnimatedButton
 *  com.aresclient.visuals.utils.items.ImageButtonWithText
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package com.aresclient.visuals;

import com.aresclient.discordrpc.DiscordApp;
import com.aresclient.utils.config.ConfigManager;
import com.aresclient.visuals.GuiCosmetics;
import com.aresclient.visuals.GuiModSettings;
import com.aresclient.visuals.GuiTimechanger;
import com.aresclient.visuals.hud.ui.GuiHud;
import com.aresclient.visuals.utils.items.AnimatedButton;
import com.aresclient.visuals.utils.items.ImageButtonWithText;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/*
 * Exception performing whole class analysis ignored.
 */
public class GuiAresMenu
extends GuiScreen {
    public static int Icon = 1;
    private float oldMouseX;
    private float oldMouseY;
    public static int prevFramerate = 0;

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        DiscordApp.update((String)(this.mc.isIntegratedServerRunning() ? "Singeplayer" : this.mc.getCurrentServerData().serverIP), (String)"Ingame Menu", (boolean)true);
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.oldMouseX = mouseX;
        this.oldMouseY = mouseY;
        int x = width / 2 + width / 3;
        int y = height / 2 + height / 6;
        int i = -100;
        int j = 10;
        GuiAresMenu.drawEntityOnScreen((int)(i + 51), (int)(j + 75), (int)30, (float)((float)(i + 51) - this.oldMouseX), (float)((float)(j + 75 - 50) - this.oldMouseY), (EntityLivingBase)this.mc.thePlayer);
        GuiAresMenu.drawEntityOnScreen((int)x, (int)y, (int)50, (float)((float)x - this.oldMouseX), (float)((float)y - this.oldMouseY - 75.0f), (EntityLivingBase)this.mc.thePlayer);
    }

    public void initGui() {
        prevFramerate = this.mc.gameSettings.limitFramerate;
        this.mc.gameSettings.limitFramerate = 60;
        Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        super.initGui();
        this.updatebuttons();
    }

    private void updatebuttons() {
        int s = height / 10;
        int k = s * 3 - s / 2;
        this.buttonList.clear();
        this.buttonList.add(new AnimatedButton(1, 12, k - s * 1 + 2, s - 4, s - 4, "Ares/Icons/Animated/Settings/Settings ("));
        this.buttonList.add(new AnimatedButton(2, 10, k, s, s, "Ares/Icons/Animated/Cosmetics/Cosmetics ("));
        this.buttonList.add(new AnimatedButton(3, 10, k + s * 1, s, s, "Ares/Icons/Animated/Mods/Mods ("));
        this.buttonList.add(new AnimatedButton(5, 10, k + s * 2, s, s, "Ares/Icons/Animated/Multiplayer/Multiplayer ("));
        this.buttonList.add(new AnimatedButton(6, 10, k + s * 3, s, s, "Ares/Icons/Animated/Timechanger/Timechanger ("));
        this.buttonList.add(new AnimatedButton(7, 10, k + s * 4, s, s, "Ares/Icons/Animated/Exit/Exit ("));
        int s1 = height / 8;
        int k1 = s1 * 3 - s1 / 2;
        this.buttonList.add(new ImageButtonWithText(8, width / 2 - s1 * 2, k1 - s1 * 1, s1, s1, new ResourceLocation("Ares/Icons/Toggles/ClientSettings/Timer" + ConfigManager.settings.Timer + ".png"), "PlayTime", I18n.format((String)"ares.mods.playtime", (Object[])new Object[0])));
        this.buttonList.add(new ImageButtonWithText(9, width / 2 - s1 * 3, k1 - s1 * 1, s1, s1, new ResourceLocation("Ares/Icons/Toggles/ClientSettings/movemods.png"), "Move HUD", String.valueOf(I18n.format((String)"ares.mods.movehud", (Object[])new Object[0])) + " " + GameSettings.getKeyDisplayString((int)this.mc.gameSettings.keyBindAresHud.getKeyCode()) + "."));
        this.buttonList.add(new ImageButtonWithText(10, width / 2 - s1, k1 - s1 * 1, s1, s1, new ResourceLocation("Ares/Icons/Toggles/ClientSettings/bossbar" + ConfigManager.settings.Bossbar + ".png"), "Bossbar", I18n.format((String)"ares.mods.bossbar", (Object[])new Object[0])));
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 9) {
            this.updatebuttons();
        }
        if (button.id == 8) {
            Icon = Icon <= 2 ? ++Icon : 1;
            this.updatebuttons();
        }
        if (button.id == 9) {
            this.mc.displayGuiScreen((GuiScreen)new GuiHud());
        }
        if (button.id == 7) {
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen((GuiScreen)new GuiAresMenu());
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen((GuiScreen)new GuiCosmetics());
        }
        if (button.id == 3) {
            this.mc.displayGuiScreen((GuiScreen)new GuiModSettings());
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
        }
        if (button.id == 6) {
            this.mc.displayGuiScreen((GuiScreen)new GuiTimechanger());
        }
        if (button.id == 8) {
            ConfigManager.settings.Timer = ConfigManager.settings.Timer == 1 ? 2 : 1;
            this.updatebuttons();
        }
        if (button.id == 10) {
            ConfigManager.settings.Bossbar = ConfigManager.settings.Bossbar == 1 ? 2 : 1;
            this.updatebuttons();
        }
    }

    public void onGuiClosed() {
        if (this.mc.thePlayer != null) {
            this.mc.gameSettings.limitFramerate = prevFramerate;
            Minecraft.getMinecraft().entityRenderer.loadEntityShader(null);
            super.onGuiClosed();
        }
    }

    public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, EntityLivingBase ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)posX, (float)posY, (float)50.0f);
        GlStateManager.scale((float)(-scale), (float)scale, (float)scale);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-((float)Math.atan(mouseY / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        ent.renderYawOffset = (float)Math.atan(mouseX / 40.0f) * 20.0f;
        ent.rotationYaw = (float)Math.atan(mouseX / 40.0f) * 40.0f;
        ent.rotationPitch = -((float)Math.atan(mouseY / 40.0f)) * 20.0f;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0f);
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntityWithPosYaw((Entity)ent, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }
}


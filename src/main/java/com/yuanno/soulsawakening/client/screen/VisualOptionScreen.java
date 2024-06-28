package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncMiscDataPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class VisualOptionScreen extends Screen {
    protected VisualOptionScreen() {
        super(new StringTextComponent(""));
    }

    @Override
    public void init()
    {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        this.buttons.clear();
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2 + 40;
        IMiscData config = MiscDataCapability.get(player);
        String textAbilityOverlay = "";
        if (config.getCanRenderOverlay())
            textAbilityOverlay = "keys.soulsawakening.enabled";
        else
            textAbilityOverlay = "keys.soulsawakening.disabled";
        posX += 80;
        this.addButton(new Button(posX - 50, posY - 28, 70, 20, new TranslationTextComponent(textAbilityOverlay), b ->
        {
            config.setCanRenderOverlay(!config.getCanRenderOverlay());
            PacketHandler.sendToServer(new CSyncMiscDataPacket(config));
            init();
        }));
        String textRaceOverlay = "";
        if (config.getRenderRaceOverlay())
            textRaceOverlay = "keys.soulsawakening.enabled";
        else
            textRaceOverlay = "keys.soulsawakening.disabled";
        posY += 30;
        this.addButton(new Button(posX - 50, posY - 28, 70, 20, new TranslationTextComponent(textRaceOverlay), b ->
        {
            config.setRenderRaceOverlay(!config.getRenderRaceOverlay());
            PacketHandler.sendToServer(new CSyncMiscDataPacket(config));
            init();
        }));
        String canZanpakutoOverlay = "";
        if (config.getRenderZanpakutoOverlay())
            canZanpakutoOverlay = "keys.soulsawakening.enabled";
        else
            canZanpakutoOverlay = "keys.soulsawakening.disabled";
        posY += 40;
        this.addButton(new Button(posX - 50, posY - 32, 70, 20, new TranslationTextComponent(canZanpakutoOverlay), b ->
        {
            config.setRenderZanpakutoOverlay(!config.getRenderZanpakutoOverlay());
            PacketHandler.sendToServer(new CSyncMiscDataPacket(config));
            init();
        }));
        if (!config.getUnlockedZanpakutoStyle().isEmpty())
        {
            this.addButton(new Button(posX - 50, posY, 70, 20, new TranslationTextComponent("soulsawakening.styles." + config.getZanpakutoStyle()), b ->
            {
                config.setZanpakutoStyle(nextZanpakutoStyle(config));
                PacketHandler.sendToServer(new CSyncMiscDataPacket(config));
                init();
            }));
        }
    }

    private static String nextZanpakutoStyle(IMiscData config) {
        String nextStyle = "";
        for(int i = 0; i < config.getUnlockedZanpakutoStyle().size(); i++)
        {
            if (config.getUnlockedZanpakutoStyle().get(i).equals(config.getZanpakutoStyle()))
            {
                if (i == config.getUnlockedZanpakutoStyle().size() - 1)
                    nextStyle = config.getUnlockedZanpakutoStyle().get(0);
                else
                    nextStyle = config.getUnlockedZanpakutoStyle().get(i + 1);
                return nextStyle;
            }
        }
        return nextStyle;
    }

    @Override
    public void render(MatrixStack matrixStack, int xMouse, int yMouse, float f)
    {
        int posX = (this.width - 256) / 2 + 110;
        int posY = (this.height - 256) / 2 + 40;

        this.renderBackground(matrixStack);
        drawString(matrixStack, font, TextFormatting.WHITE+ "Ability overlay:", posX - 190, posY - 22, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.WHITE+ "Race overlay:", posX - 190, posY + 12, Color.GRAY.getRGB());
        drawString(matrixStack, font, TextFormatting.WHITE+ "Zanpakuto overlay:", posX - 190, posY + 42, Color.GRAY.getRGB());
        renderEntityInInventory(posX + 80, posY + 85, 60, 0, 0, this.minecraft.player);
        renderEntityBackInInventory(posX + 80, posY + 210, 60, 0, 0, this.minecraft.player);
        renderEntityBackInInventorySideLeft(posX + 160, posY + 85, 60, 0, 0, this.minecraft.player);
        renderEntityBackInInventorySideRight(posX + 160, posY + 210, 60, 0, 0, this.minecraft.player);

        super.render(matrixStack, xMouse, yMouse, f);
    }

    public static void renderEntityInInventory(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
        float lvt_6_1_ = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float lvt_7_1_ = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack lvt_8_1_ = new MatrixStack();
        lvt_8_1_.translate(0.0, 0.0, 1000.0);
        lvt_8_1_.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion lvt_9_1_ = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion lvt_10_1_ = Vector3f.XP.rotationDegrees(lvt_7_1_ * 20.0F);
        lvt_9_1_.mul(lvt_10_1_);
        lvt_8_1_.mulPose(lvt_9_1_);
        float lvt_11_1_ = p_228187_5_.yBodyRot;
        float lvt_12_1_ = p_228187_5_.yRot;
        float lvt_13_1_ = p_228187_5_.xRot;
        float lvt_14_1_ = p_228187_5_.yHeadRotO;
        float lvt_15_1_ = p_228187_5_.yHeadRot;
        p_228187_5_.yBodyRot = 180.0F + lvt_6_1_ * 20.0F;
        p_228187_5_.yRot = 180.0F + lvt_6_1_ * 40.0F;
        p_228187_5_.xRot = -lvt_7_1_ * 20.0F;
        p_228187_5_.yHeadRot = p_228187_5_.yRot;
        p_228187_5_.yHeadRotO = p_228187_5_.yRot;
        EntityRendererManager lvt_16_1_ = Minecraft.getInstance().getEntityRenderDispatcher();
        lvt_10_1_.conj();
        lvt_16_1_.overrideCameraOrientation(lvt_10_1_);
        lvt_16_1_.setRenderShadow(false);
        IRenderTypeBuffer.Impl lvt_17_1_ = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            lvt_16_1_.render(p_228187_5_, 0.0, 0.0, 0.0, 0.0F, 1.0F, lvt_8_1_, lvt_17_1_, 15728880);
        });
        lvt_17_1_.endBatch();
        lvt_16_1_.setRenderShadow(true);
        p_228187_5_.yBodyRot = lvt_11_1_;
        p_228187_5_.yRot = lvt_12_1_;
        p_228187_5_.xRot = lvt_13_1_;
        p_228187_5_.yHeadRotO = lvt_14_1_;
        p_228187_5_.yHeadRot = lvt_15_1_;
        RenderSystem.popMatrix();
    }
    public static void renderEntityBackInInventory(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
        float lvt_6_1_ = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float lvt_7_1_ = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack lvt_8_1_ = new MatrixStack();
        lvt_8_1_.translate(0.0, 0.0, 1000.0);
        lvt_8_1_.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion lvt_9_1_ = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion lvt_10_1_ = Vector3f.XP.rotationDegrees(lvt_7_1_ * 20.0F);
        lvt_9_1_.mul(lvt_10_1_);
        lvt_8_1_.mulPose(lvt_9_1_);
        float lvt_11_1_ = p_228187_5_.yBodyRot;
        float lvt_12_1_ = p_228187_5_.yRot;
        float lvt_13_1_ = p_228187_5_.xRot;
        float lvt_14_1_ = p_228187_5_.yHeadRotO;
        float lvt_15_1_ = p_228187_5_.yHeadRot;
        p_228187_5_.yBodyRot = 360 + lvt_6_1_ * 20.0F;
        p_228187_5_.yRot = 360 + lvt_6_1_ * 40.0F;
        p_228187_5_.xRot = -lvt_7_1_ * 20.0F;
        p_228187_5_.yHeadRot = p_228187_5_.yRot;
        p_228187_5_.yHeadRotO = p_228187_5_.yRot;
        EntityRendererManager lvt_16_1_ = Minecraft.getInstance().getEntityRenderDispatcher();
        lvt_10_1_.conj();
        lvt_16_1_.overrideCameraOrientation(lvt_10_1_);
        lvt_16_1_.setRenderShadow(false);
        IRenderTypeBuffer.Impl lvt_17_1_ = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            lvt_16_1_.render(p_228187_5_, 0.0, 0.0, 0.0, 0.0F, 1.0F, lvt_8_1_, lvt_17_1_, 15728880);
        });
        lvt_17_1_.endBatch();
        lvt_16_1_.setRenderShadow(true);
        p_228187_5_.yBodyRot = lvt_11_1_;
        p_228187_5_.yRot = lvt_12_1_;
        p_228187_5_.xRot = lvt_13_1_;
        p_228187_5_.yHeadRotO = lvt_14_1_;
        p_228187_5_.yHeadRot = lvt_15_1_;
        RenderSystem.popMatrix();
    }
    public static void renderEntityBackInInventorySideLeft(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
        float lvt_6_1_ = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float lvt_7_1_ = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack lvt_8_1_ = new MatrixStack();
        lvt_8_1_.translate(0.0, 0.0, 1000.0);
        lvt_8_1_.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion lvt_9_1_ = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion lvt_10_1_ = Vector3f.XP.rotationDegrees(lvt_7_1_ * 20.0F);
        lvt_9_1_.mul(lvt_10_1_);
        lvt_8_1_.mulPose(lvt_9_1_);
        float lvt_11_1_ = p_228187_5_.yBodyRot;
        float lvt_12_1_ = p_228187_5_.yRot;
        float lvt_13_1_ = p_228187_5_.xRot;
        float lvt_14_1_ = p_228187_5_.yHeadRotO;
        float lvt_15_1_ = p_228187_5_.yHeadRot;
        p_228187_5_.yBodyRot = 270 + lvt_6_1_ * 20.0F;
        p_228187_5_.yRot = 270 + lvt_6_1_ * 40.0F;
        p_228187_5_.xRot = -lvt_7_1_ * 20.0F;
        p_228187_5_.yHeadRot = p_228187_5_.yRot;
        p_228187_5_.yHeadRotO = p_228187_5_.yRot;
        EntityRendererManager lvt_16_1_ = Minecraft.getInstance().getEntityRenderDispatcher();
        lvt_10_1_.conj();
        lvt_16_1_.overrideCameraOrientation(lvt_10_1_);
        lvt_16_1_.setRenderShadow(false);
        IRenderTypeBuffer.Impl lvt_17_1_ = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            lvt_16_1_.render(p_228187_5_, 0.0, 0.0, 0.0, 0.0F, 1.0F, lvt_8_1_, lvt_17_1_, 15728880);
        });
        lvt_17_1_.endBatch();
        lvt_16_1_.setRenderShadow(true);
        p_228187_5_.yBodyRot = lvt_11_1_;
        p_228187_5_.yRot = lvt_12_1_;
        p_228187_5_.xRot = lvt_13_1_;
        p_228187_5_.yHeadRotO = lvt_14_1_;
        p_228187_5_.yHeadRot = lvt_15_1_;
        RenderSystem.popMatrix();
    }
    public static void renderEntityBackInInventorySideRight(int p_228187_0_, int p_228187_1_, int p_228187_2_, float p_228187_3_, float p_228187_4_, LivingEntity p_228187_5_) {
        float lvt_6_1_ = (float)Math.atan((double)(p_228187_3_ / 40.0F));
        float lvt_7_1_ = (float)Math.atan((double)(p_228187_4_ / 40.0F));
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)p_228187_0_, (float)p_228187_1_, 1050.0F);
        RenderSystem.scalef(1.0F, 1.0F, -1.0F);
        MatrixStack lvt_8_1_ = new MatrixStack();
        lvt_8_1_.translate(0.0, 0.0, 1000.0);
        lvt_8_1_.scale((float)p_228187_2_, (float)p_228187_2_, (float)p_228187_2_);
        Quaternion lvt_9_1_ = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion lvt_10_1_ = Vector3f.XP.rotationDegrees(lvt_7_1_ * 20.0F);
        lvt_9_1_.mul(lvt_10_1_);
        lvt_8_1_.mulPose(lvt_9_1_);
        float lvt_11_1_ = p_228187_5_.yBodyRot;
        float lvt_12_1_ = p_228187_5_.yRot;
        float lvt_13_1_ = p_228187_5_.xRot;
        float lvt_14_1_ = p_228187_5_.yHeadRotO;
        float lvt_15_1_ = p_228187_5_.yHeadRot;
        p_228187_5_.yBodyRot = 90 + lvt_6_1_ * 20.0F;
        p_228187_5_.yRot = 90 + lvt_6_1_ * 40.0F;
        p_228187_5_.xRot = -lvt_7_1_ * 20.0F;
        p_228187_5_.yHeadRot = p_228187_5_.yRot;
        p_228187_5_.yHeadRotO = p_228187_5_.yRot;
        EntityRendererManager lvt_16_1_ = Minecraft.getInstance().getEntityRenderDispatcher();
        lvt_10_1_.conj();
        lvt_16_1_.overrideCameraOrientation(lvt_10_1_);
        lvt_16_1_.setRenderShadow(false);
        IRenderTypeBuffer.Impl lvt_17_1_ = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            lvt_16_1_.render(p_228187_5_, 0.0, 0.0, 0.0, 0.0F, 1.0F, lvt_8_1_, lvt_17_1_, 15728880);
        });
        lvt_17_1_.endBatch();
        lvt_16_1_.setRenderShadow(true);
        p_228187_5_.yBodyRot = lvt_11_1_;
        p_228187_5_.yRot = lvt_12_1_;
        p_228187_5_.xRot = lvt_13_1_;
        p_228187_5_.yHeadRotO = lvt_14_1_;
        p_228187_5_.yHeadRot = lvt_15_1_;
        RenderSystem.popMatrix();
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new VisualOptionScreen());
    }

}

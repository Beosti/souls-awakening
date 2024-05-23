package com.yuanno.soulsawakening.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.ability.api.interfaces.IContinuousAbility;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.awt.*;

/**
 * Visual overlays for abilities
 */
@OnlyIn(Dist.CLIENT)
public class AbilityOverlay extends AbstractGui {

    @SubscribeEvent
    public void renderStatsOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        ClientPlayerEntity player = Minecraft.getInstance().player;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);
        if (!miscData.getCanRenderOverlay())
            return;

        String race = entityStats.getRace();
        if ((race.equals(ModValues.SHINIGAMI) || race.equals(ModValues.FULLBRINGER)) && (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem))) {
            return;
        }

        for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++) {
            Ability abilityToDraw = abilityData.getUnlockedAbilities().get(i);
            if (!abilityToDraw.getCategory().equals(Ability.Category.KIDO)) {
                int iconX = 20;
                int iconY = 20 + i * 20;

                // Drawing the ability icon first
                Color iconColor = Beapi.hexToRGB("#FFFFFF");
                String originalResourceLocation = abilityToDraw.getRegistryName().toString();
                String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                Beapi.drawIcon(resourceLocation, iconX, iconY, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);

                // Drawing the widget on top of the icon
                ResourceLocation widgetResourceLocation = new ResourceLocation(Main.MODID, "textures/widget/widget_contour.png");
                if (abilityToDraw.getState().equals(Ability.STATE.COOLDOWN)) {
                    Beapi.drawIcon(widgetResourceLocation, iconX, iconY, 1, 16, 16, 1.0f, 0, 0); // Red color for cooldown
                } else if (abilityToDraw.getState().equals(Ability.STATE.CONTINUOUS)) {
                    Beapi.drawIcon(widgetResourceLocation, iconX, iconY, 1, 16, 16, 0, 0, 1.0f); // Blue color for continuous
                } else {
                    Beapi.drawIcon(widgetResourceLocation, iconX, iconY, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f); // Default color
                }

                // Drawing the timers on top of the widget
                MatrixStack matrixStack = event.getMatrixStack();

                if (abilityToDraw.getState().equals(Ability.STATE.CONTINUOUS) && abilityToDraw instanceof IContinuousAbility) {
                    if (((IContinuousAbility) abilityToDraw).getMaxTimer() != -1) {
                        int timer = ((IContinuousAbility) abilityToDraw).getMaxTimer() - abilityToDraw.getTimer();
                        drawTimer(matrixStack, timer, iconX, iconY, i);
                    }
                } else if (abilityToDraw.getState().equals(Ability.STATE.COOLDOWN)) {
                    int cooldown = (int) abilityToDraw.getCooldown();
                    drawTimer(matrixStack, cooldown, iconX, iconY, i);
                }
            }
        }
    }

    private void drawTimer(MatrixStack matrixStack, int timer, int iconX, int iconY, int index) {
        matrixStack.pushPose();
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        matrixStack.translate(1, 1, 1000); // Move the text to a higher z-level
        int stringWidth = Minecraft.getInstance().font.width(String.valueOf(timer));
        int posX = (int) ((28 - 1 - (stringWidth / 5)) / 0.5f);
        Beapi.drawStringWithBorder(Minecraft.getInstance().font, matrixStack, TextFormatting.WHITE + "" + timer, posX, (int) ((26 + index * 20) / 0.5), 0);
        matrixStack.popPose();
    }

}

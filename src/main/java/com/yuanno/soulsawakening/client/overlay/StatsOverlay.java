package com.yuanno.soulsawakening.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
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

@OnlyIn(Dist.CLIENT)
public class StatsOverlay extends AbstractGui {

    @SubscribeEvent
    public void renderStatsOverlay(RenderGameOverlayEvent.Post event)
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        IMiscData miscData = MiscDataCapability.get(player);
        if (!miscData.getCanRenderOverlay())
            return;
        String race = entityStats.getRace();

        if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE)
        {
            //drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + "RACE: " + TextFormatting.RESET + race, 330, 20, -1);
            if ((race.equals(ModValues.SHINIGAMI) || race.equals(ModValues.FULLBRINGER)) && (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem) || entityStats.getShinigamiStats().getZanjutsuPoints() < 10))
                return;
            MatrixStack matrixStack = event.getMatrixStack();
            matrixStack.pushPose();
            for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
            {
                if (!abilityData.getUnlockedAbilities().get(i).getCategory().equals(Ability.Category.KIDO))
                {
                    Color iconColor = Beapi.hexToRGB("#FFFFFF");
                    Ability abilityToDraw = abilityData.getUnlockedAbilities().get(i);

                    String originalResourceLocation = abilityToDraw.getRegistryName().toString();
                    String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                    ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                    Beapi.drawIcon(resourceLocation, 20, 20 + i * 20, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                    ResourceLocation widgetResourceLocation = new ResourceLocation(Main.MODID, "textures/widget/widget_contour.png");
                    if (!abilityToDraw.getState().equals(Ability.STATE.COOLDOWN))
                        Beapi.drawIcon(widgetResourceLocation, 20, 20 + i * 20, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                    else {
                        int cooldown = (int) abilityToDraw.getCooldown();
                        Beapi.drawIcon(widgetResourceLocation, 20, 20 + i * 20, 1, 16, 16, 1.0f, 0, 0);
                        matrixStack.scale(0.5f, 0.5f, 0.5f);
                        matrixStack.translate(1, 1, 1);
                        int stringWidth = Minecraft.getInstance().font.width(String.valueOf(cooldown));

                        int posX = (int) ((20 + (16 - stringWidth) / 2) / 0.5f);
                        Beapi.drawStringWithBorder(Minecraft.getInstance().font, matrixStack, TextFormatting.WHITE + "" + cooldown, posX, (int) ((26 + i * 20) / 0.5), 0);
                    }
                }
            }
            matrixStack.popPose();
        }
    }
}

package com.yuanno.soulsawakening.client.overlay;

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

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            //drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + "RACE: " + TextFormatting.RESET + race, 330, 20, -1);
            if ((race.equals(ModValues.SHINIGAMI) || race.equals(ModValues.FULLBRINGER)) && (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem) || entityStats.getZanjutsuPoints() < 10))
                return;
            int rgb = Color.RED.getRGB();

            for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
            {
                if (abilityData.getUnlockedAbilities().get(i).getActivationType() != null && !abilityData.getUnlockedAbilities().get(i).getActivationType().equals(Ability.ActivationType.SCROLL))
                {
                    Color iconColor = Beapi.hexToRGB("#FFFFFF");
                    Ability abilityToDraw = abilityData.getUnlockedAbilities().get(i);
                    String originalResourceLocation = abilityToDraw.getRegistryName().toString();
                    String formattedResourceLocation = originalResourceLocation.replaceAll("_", "").replaceAll("soulsawakening:", "");
                    ResourceLocation resourceLocation = new ResourceLocation(Main.MODID, "textures/ability/" + formattedResourceLocation + ".png");
                    Beapi.drawIcon(resourceLocation, 20, 20 + i * 20, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                    ResourceLocation widgetResourceLocation = new ResourceLocation(Main.MODID, "textures/widget/widget_contour.png");
                    ResourceLocation widgetResourceLocationCooldown = new ResourceLocation(Main.MODID, "textures/widget/widget_contour_cooldown.png");

                    if (abilityToDraw.getCooldown() != 0 && abilityToDraw.getCooldown() != abilityToDraw.getMaxCooldown())
                        Beapi.drawIcon(widgetResourceLocation, 20, 20 + i * 20, 1, 16, 16, 1.0f, 0, 0);
                    else {
                        Beapi.drawIcon(widgetResourceLocation, 20, 20 + i * 20, 1, 16, 16, iconColor.getRed() / 255.0f, iconColor.getGreen() / 255.0f, iconColor.getBlue() / 255.0f);
                    }
                }
            }
        }
    }
}

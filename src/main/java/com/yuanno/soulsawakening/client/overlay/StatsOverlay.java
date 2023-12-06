package com.yuanno.soulsawakening.client.overlay;

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
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
            drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + "RACE: " + TextFormatting.RESET + race, 330, 20, -1);
            if ((race.equals(ModValues.SHINIGAMI) || race.equals(ModValues.FULLBRINGER)) && (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem) || entityStats.getZanjutsuPoints() < 10))
                return;
            for (int i = 0; i < abilityData.getUnlockedAbilities().size(); i++)
            {
                if (!abilityData.getUnlockedAbilities().get(i).getPassive()) {
                    drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + "Ability: " + TextFormatting.RESET + abilityData.getUnlockedAbilities().get(i).getName(), 20, 20 + i * 15, -1);

                    drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + "Cooldown: " + TextFormatting.RESET + (int) abilityData.getUnlockedAbilities().get(i).getCooldown(), 130, 20 + i * 15, -1);
                }
                else if (abilityData.getUnlockedAbilities().get(i).getPassive() && abilityData.getUnlockedAbilities().get(i).getShown())
                    drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + "Ability: " + TextFormatting.RESET + abilityData.getUnlockedAbilities().get(i).getName(), 20, 20 + i * 15, -1);

            }
        }
    }
}

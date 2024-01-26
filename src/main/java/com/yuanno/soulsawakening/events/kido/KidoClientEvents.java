package com.yuanno.soulsawakening.events.kido;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
public class KidoClientEvents extends AbstractGui {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            IAbilityData abilityData = AbilityDataCapability.get(Minecraft.getInstance().player);
            //System.out.println(abilityData.getSelectedAbility());
            if (!abilityData.getAbilitiesInBar().isEmpty() && abilityData.getAbilitiesInBar().get(abilityData.getSelectionAbility()) != null) {
                drawString(event.getMatrixStack(), Minecraft.getInstance().font, TextFormatting.BOLD + abilityData.getAbilitiesInBar().get(abilityData.getSelectionAbility()).getName(), 363, 195, -1);
            }
        }
    }
}

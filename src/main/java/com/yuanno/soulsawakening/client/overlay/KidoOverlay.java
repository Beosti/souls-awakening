package com.yuanno.soulsawakening.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class KidoOverlay extends AbstractGui {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event)
    {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            IAbilityData abilityData = AbilityDataCapability.get(Minecraft.getInstance().player);
            //System.out.println(abilityData.getSelectedAbility());
            MatrixStack matrixStack = event.getMatrixStack();
            ArrayList<Ability> abilities = abilityData.getAbilitiesInBar();
            int selectedAbilityInteger = abilityData.getSelectionAbility();
            Ability selectedAbility;
            Ability previousAbility;
            Ability upComingAbility;
            if (!(abilities.isEmpty() && abilities.get(selectedAbilityInteger) != null))
                return;
            selectedAbility = abilities.get(selectedAbilityInteger);
            if (selectedAbilityInteger == 0)
                previousAbility = abilities.get(abilities.size());
            else
                previousAbility = abilities.get(selectedAbilityInteger - 1);
            if (selectedAbilityInteger >= abilities.size())
                upComingAbility = abilities.get(0);
            else
                upComingAbility = abilities.get(selectedAbilityInteger + 1);
        }
    }
}

package com.yuanno.soulsawakening.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

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
            if (abilities.isEmpty())
                return;
            if (abilities.get(selectedAbilityInteger) == null)
                return;
            selectedAbility = abilities.get(selectedAbilityInteger);
            if (selectedAbilityInteger == 0)
                previousAbility = abilities.get(abilities.size() - 1);
            else
                previousAbility = abilities.get(selectedAbilityInteger - 1);
            if (selectedAbilityInteger >= abilities.size() - 1)
                upComingAbility = abilities.get(0);
            else
                upComingAbility = abilities.get(selectedAbilityInteger + 1);

            // drawing previous one
            int previousSpell = (int) (288 / 0.7) - previousAbility.getName().length();
            matrixStack.pushPose();
            matrixStack.scale(0.7f, 0.7f, 0.7f);
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL))
                drawString(matrixStack, Minecraft.getInstance().font, TextFormatting.BOLD + previousAbility.getName(), previousSpell, (int) ((195 / 0.7)), -1);
            matrixStack.popPose();
            // drawing current in use spell
            drawString(matrixStack, Minecraft.getInstance().font, TextFormatting.BOLD + selectedAbility.getName(), 330 - selectedAbility.getName().length(), 195, -1);
            // drawing upcoming one
            int upcomingSpell = (int) (383 / 0.7);
            matrixStack.pushPose();
            matrixStack.scale(0.7f, 0.7f, 0.7f);
            if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_CONTROL))
                drawString(matrixStack, Minecraft.getInstance().font, TextFormatting.BOLD + upComingAbility.getName(), upcomingSpell, (int) (195 / 0.7), -1);
            matrixStack.popPose();

        }
    }
}

package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.quests.Objective;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class QuestListScreen extends Screen {
    private PlayerEntity player;
    private IQuestData questData;


    int state = 1;
    public QuestListScreen()
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = minecraft.player;
        this.questData = QuestDataCapability.get(player);

    }


    @Override
    public void init()
    {
        super.init();
        int posX = this.width / 2;
        int posY = this.height / 2;
        //System.out.println(buttons);
    }


    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = this.width / 2;
        int posY = this.height / 2;
        questRendering(matrixStack);

        super.render(matrixStack, x, y, f);
    }

    public void questRendering(MatrixStack matrixStack)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        int leftShift = posX - 20;
        for (int i = 0; i < questData.getQuests().size(); i++) {

            if (!questData.getQuests().get(i).getIsInProgress())
                continue;

            // Display quest title
            drawString(matrixStack, this.font, TextFormatting.BOLD + "Quest" + ": " + questData.getQuests().get(i).getTitle(), leftShift, posY + 20 + (i * 15), -1);

            // Display objectives for the current quest
            for (int ia = 0; ia < questData.getQuests().get(i).getObjectives().size(); ia++) {
                Objective objective = questData.getQuests().get(i).getObjectives().get(ia);
                String objectiveString = "Objective" + ": " + objective.getTitle();
                int stringWidth = this.font.width(objectiveString);
                drawString(matrixStack, this.font, objectiveString, leftShift, posY + 20 + (i * 15) + ((ia + 1) * 15), -1);
                if (objective.getProgress() < objective.maxProgress)
                    drawString(matrixStack, this.font, objective.getProgress() + "/" + objective.getMaxProgress(), leftShift + stringWidth + 12, posY + 20 + (i * 15) + ((ia + 1) * 15), -1);
                else
                    drawString(matrixStack, this.font, TextFormatting.BOLD + "completed", leftShift + stringWidth + 12, posY + 20 + (i * 15) + ((ia + 1) * 15), -1);
            }
        }

    }
    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new QuestListScreen());
    }
}

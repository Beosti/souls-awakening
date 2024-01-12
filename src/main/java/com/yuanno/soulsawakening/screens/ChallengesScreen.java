package com.yuanno.soulsawakening.screens;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.challenges.BasicShinigamiChallenge;
import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.challenges.IChallengesData;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CStartChallengePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChallengesScreen extends Screen {
    private final PlayerEntity player;

    private final LivingEntity[] group = new LivingEntity[1];
    private final IChallengesData challengesDataBase;
    protected ChallengesScreen() {
        super(new StringTextComponent(""));
        this.player = Minecraft.getInstance().player;
        this.group[0] = this.player;
        this.challengesDataBase = ChallengesDataCapability.get(player);
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new ChallengesScreen());
    }



    @Override
    public void init()
    {
        int posX = ((this.width - 256) / 2);
        int posY = (this.height - 256) / 2;
        this.addButton(new Button(posX - 65, posY + 20, 60, 20, new TranslationTextComponent("Shinigami"), b ->
        {
            PacketHandler.sendToServer(new CStartChallengePacket(ModChallenges.BASIC_SHINIGAMI.get().getRegistryName(), this.group, false));
            this.minecraft.setScreen(null);
        }, (button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("Challenge a basic shinigami"), mouseX, mouseY);
        })).active = challengesDataBase.hasChallenge(ModChallenges.BASIC_SHINIGAMI.get());
        this.addButton(new Button(posX - 65, posY + 40, 60, 20, new TranslationTextComponent("Rank 20"), b ->
        {
            PacketHandler.sendToServer(new CStartChallengePacket(ModChallenges.SEATED20_SHINIGAMI.get().getRegistryName(), this.group, false));
            this.minecraft.setScreen(null);
        }, (button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("Challenge a seated officer rank 20 shinigami"), mouseX, mouseY);
        })).active = challengesDataBase.hasChallenge(ModChallenges.SEATED20_SHINIGAMI.get()) || challengesDataBase.isChallengeCompleted(ModChallenges.SEATED20_SHINIGAMI.get());

    }
}

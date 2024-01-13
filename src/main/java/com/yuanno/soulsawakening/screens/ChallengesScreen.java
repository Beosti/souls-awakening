package com.yuanno.soulsawakening.screens;

import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
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

        addChallengeButton(posX - 65, posY + 20, ModChallenges.BASIC_SHINIGAMI.get(), "Shinigami", "Challenge a basic shinigami");
        addChallengeButton(posX - 65, posY + 40, ModChallenges.SEATED20_SHINIGAMI.get(), "Rank 20", "Challenge a seated officer rank 20 shinigami", true);
        addChallengeButton(posX - 65, posY + 60, ModChallenges.SEATED19_SHINIGAMI.get(), "Rank 19", "Challenge a seated officer rank 19 shinigami", true);
        addChallengeButton(posX - 65, posY + 80, ModChallenges.SEATED18_SHINIGAMI.get(), "Rank 18", "Challenge a seated officer rank 18 shinigami", true);
        addChallengeButton(posX - 65, posY + 100, ModChallenges.SEATED17_SHINIGAMI.get(), "Rank 17", "Challenge a seated officer rank 17 shinigami", true);
        addChallengeButton(posX - 65, posY + 120, ModChallenges.SEATED16_SHINIGAMI.get(), "Rank 16", "Challenge a seated officer rank 16 shinigami", true);
        addChallengeButton(posX - 65, posY + 140, ModChallenges.SEATED15_SHINIGAMI.get(), "Rank 15", "Challenge a seated officer rank 15 shinigami", true);
        addChallengeButton(posX - 65, posY + 160, ModChallenges.SEATED14_SHINIGAMI.get(), "Rank 14", "Challenge a seated officer rank 14 shinigami", true);
        addChallengeButton(posX - 65, posY + 180, ModChallenges.SEATED13_SHINIGAMI.get(), "Rank 13", "Challenge a seated officer rank 13 shinigami", true);
        addChallengeButton(posX - 65, posY + 200, ModChallenges.SEATED12_SHINIGAMI.get(), "Rank 12", "Challenge a seated officer rank 12 shinigami", true);
        addChallengeButton(posX - 65, posY + 220, ModChallenges.SEATED11_SHINIGAMI.get(), "Rank 11", "Challenge a seated officer rank 11 shinigami", true);

        /*
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
        this.addButton(new Button(posX - 65, posY + 40, 60, 20, new TranslationTextComponent("Rank 19"), b ->
        {
            PacketHandler.sendToServer(new CStartChallengePacket(ModChallenges.SEATED19_SHINIGAMI.get().getRegistryName(), this.group, false));
            this.minecraft.setScreen(null);
        }, (button, matrixStack, mouseX, mouseY) ->
        {
            if (button.isHovered())
                this.renderTooltip(matrixStack, new TranslationTextComponent("Challenge a seated officer rank 19 shinigami"), mouseX, mouseY);
        })).active = challengesDataBase.hasChallenge(ModChallenges.SEATED19_SHINIGAMI.get()) || challengesDataBase.isChallengeCompleted(ModChallenges.SEATED19_SHINIGAMI.get());


         */
    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText) {
        addChallengeButton(x, y, challenge, buttonText, tooltipText, false);
    }

    private void addChallengeButton(int x, int y, ChallengeCore challenge, String buttonText, String tooltipText, boolean checkCompletion) {
        Button button = new Button(x, y, 60, 20, new TranslationTextComponent(buttonText), b -> {
            PacketHandler.sendToServer(new CStartChallengePacket(challenge.getRegistryName(), this.group, false));
            this.minecraft.setScreen(null);
        }, (but, matrixStack, mouseX, mouseY) -> {
            if (but.isHovered()) {
                this.renderTooltip(matrixStack, new TranslationTextComponent(tooltipText), mouseX, mouseY);
            }
        });

        if (checkCompletion) {
            button.active = challengesDataBase.hasChallenge(challenge) || challengesDataBase.isChallengeCompleted(challenge);
        } else {
            button.active = challengesDataBase.hasChallenge(challenge);
        }

        this.addButton(button);
    }

}

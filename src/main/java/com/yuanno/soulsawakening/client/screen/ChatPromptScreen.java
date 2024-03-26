package com.yuanno.soulsawakening.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.SoulSocietyKeyAbility;
import com.yuanno.soulsawakening.ability.api.interfaces.IAttackAbility;
import com.yuanno.soulsawakening.api.SequencedString;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestRewardPacket;
import com.yuanno.soulsawakening.networking.client.CSyncGiveQuestStartPacket;
import com.yuanno.soulsawakening.networking.client.CSyncQuestDataPacket;
import com.yuanno.soulsawakening.screens.TexturedIconButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {


    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/screens/chat_background.png");
    private final ResourceLocation acceptButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/accept_button.png");
    private final ResourceLocation declineButtonTexture = new ResourceLocation(Main.MODID, "textures/gui/buttons/decline_button.png");

    private PlayerEntity player;
    private IEntityStats entityStats;
    private IQuestData questData;
    private SequencedString message = new SequencedString("", 0, 0);
    private int page = 0;
    private String text = "";
    public ChatPromptScreen()
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = minecraft.player;
        this.entityStats = EntityStatsCapability.get(player);
        this.questData = QuestDataCapability.get(player);
    }

    @Override
    public void init()
    {
        this.buttons.clear();
        this.children.clear();
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        text = "So you want to become a shinigami huh?";
        if (entityStats.getRace().equals(ModValues.SHINIGAMI))
            text = "You're already a shinigami. Scram!";
        if (QuestDataCapability.get(player).hasInProgressQuest(ModQuests.KILLHOLLOW))
            text = "You find hollows a bit everywhere in the overworld and a ton in hueco mundo. Go kill one!";
        if (QuestDataCapability.get(player).isQuestComplete(ModQuests.KILLHOLLOW))
        {
            text = "Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff";
        }
        if (this.page == -1)
            text = "I guess not everyone is cut out for this job";
        if (this.page == 1)
        {
            text = "Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.";
        }
        if (!entityStats.getRace().equals(ModValues.SPIRIT))
            text = "How did a non-spirit come here, you should be brought wherever you came from!";
        this.message = new SequencedString(text, 345, this.font.width(text) / 2, 800);
        TexturedIconButton acceptanceButton = new TexturedIconButton(acceptButtonTexture, posX + 180, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = 1;
            init();
        });
        TexturedIconButton declineButton = new TexturedIconButton(declineButtonTexture, posX + 220, posY + 232, 32, 32, new TranslationTextComponent(""), b ->
        {
            this.page = -1;
            init();
        });
        if (text.equals("So you want to become a shinigami huh?")) {
            this.addButton(acceptanceButton);
            this.addButton(declineButton);
        }

    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.minecraft.textureManager.bind(chatPrompt);
        this.blit(matrixStack, posX + 4, posY + 8, 0, 0, 256, 256);
        matrixStack.pushPose();
        matrixStack.scale(0.7f, 0.7f, 0.7f);
        this.message.render(matrixStack, (int) ((posX + 12) / 0.7), (int) ((posY + 223) / 0.7));
        matrixStack.popPose();
        super.render(matrixStack, x, y, f);
    }

    public static void open()
    {
        Minecraft.getInstance().setScreen(new ChatPromptScreen());
    }

    public void onClose()
    {
        super.onClose();
        if (this.text.equals("Good job on your first kill! You have forged a better bond with your sword, making it have a spirit also making you a real shinigami. Feel free to walk around and learn new stuff")) {
            questData.getQuest(ModQuests.KILLHOLLOW).setInProgress(false);
            PacketHandler.sendToServer(new CSyncGiveQuestRewardPacket(ModQuests.KILLHOLLOW));
        }
        if (this.text.equals("Here's a blade called a 'zanpakuto', right now it's just an asauchi(without spirit) due to you not being aware of the spirit inside. You can press alt+right click with zanpakuto to go and back to the human world. Kill a hollow and I'll make you a shinigami.")) {
            this.questData.addInProgressQuest(ModQuests.KILLHOLLOW);
            IAbilityData abilityData = AbilityDataCapability.get(player);
            abilityData.addUnlockedAbility(SoulSocietyKeyAbility.INSTANCE);
            PacketHandler.sendToServer(new CSyncGiveQuestStartPacket(ModQuests.KILLHOLLOW));
            PacketHandler.sendToServer(new CSyncQuestDataPacket(questData));
            PacketHandler.sendToServer(new CSyncAbilityDataPacket(abilityData));
            player.sendMessage(new TranslationTextComponent("This entity is now a teleport point, you can teleport back to it in your teleports menu"), Util.NIL_UUID);
        }
    }
}

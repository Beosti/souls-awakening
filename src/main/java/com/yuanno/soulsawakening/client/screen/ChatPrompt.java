package com.yuanno.soulsawakening.client.screen;

import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatPrompt {

    private IOnClose onClose = () -> {};
    private ArrayList<IChatPrompt> chatPrompts = new ArrayList<>();

    public IOnClose getOnClose()
    {
        return this.onClose;
    }
    public void setOnClose(IOnClose onClose)
    {
        this.onClose = onClose;
    }

    public ArrayList<IChatPrompt> getChatPrompts()
    {
        return this.chatPrompts;
    }
    public void setChatPrompts(ArrayList<IChatPrompt> chatPrompts)
    {
        this.chatPrompts = chatPrompts;
    }
    public void addChatPrompts(IChatPrompt chatPrompt)
    {
        this.chatPrompts.add(chatPrompt);
    }
    public void removeChatPrompts(IChatPrompt chatPrompt)
    {
        this.chatPrompts.remove(chatPrompt);
    }



    private ArrayList<Quest> quests = new ArrayList<>();

    public ArrayList<Quest> getQuests()
    {
        return this.quests;
    }
    public void setQuests(ArrayList<Quest> quests)
    {
        this.quests = quests;
    }
    public void addQuest(Quest quest)
    {
        this.quests.add(quest);
    }
    public void removeQuest(Quest quest)
    {
        this.quests.remove(quest);
    }
    /*
    private IDialogue dialogue = ();
    public interface IDialogue extends Serializable
    {
        boolean check(PlayerEntity player);
    }
    void dialogueSetup(int posX, int posY)
    {
        /*
        if (quest1)
            dialogue1
        if (quest2)
            dialogue2
        if (quest3)
            dialogue3

    }
    */
    public interface IChatPrompt extends Serializable
    {
        void chat();
    }
    public interface IOnClose extends Serializable
    {
        void onClose();
    }
}

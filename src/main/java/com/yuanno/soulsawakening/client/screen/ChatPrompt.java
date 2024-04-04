package com.yuanno.soulsawakening.client.screen;

import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ChatPrompt {

    private IOnClose onClose = () -> {};
    private ArrayList<IChatPrompt> chatPrompts = new ArrayList<>();
    protected ChatPromptScreen chatPromptScreen;
    protected boolean addAcceptanceDecline = false;
    public abstract void load();
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

    public void setChatPromptScreen(ChatPromptScreen chatPromptScreen)
    {
        this.chatPromptScreen = chatPromptScreen;
    }
    public ChatPromptScreen getChatPromptScreen()
    {
        return this.chatPromptScreen;
    }


    public interface IChatPrompt extends Serializable
    {
        void chat();
    }
    public interface IOnClose extends Serializable
    {
        void onClose();
    }
}

package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.quests.bakudoteacher.BakudoUnlockQuest;
import com.yuanno.soulsawakening.quests.bakudoteacher.HainawaUnlockQuest;
import com.yuanno.soulsawakening.quests.bakudoteacher.SekiUnlockQuest;
import com.yuanno.soulsawakening.quests.kidoteacher.ByakuraiQuest;
import com.yuanno.soulsawakening.quests.kidoteacher.KidoUnlockQuest;
import com.yuanno.soulsawakening.quests.Quest;
import com.yuanno.soulsawakening.quests.kidoteacher.TsuzuriRaidenQuest;
import com.yuanno.soulsawakening.quests.shinigamiteacher.KillHollowQuest;
import com.yuanno.soulsawakening.quests.shinigamiteacher.KillSpecificHollowQuest;
import com.yuanno.soulsawakening.quests.shinigamiteacher.RescuePlusesQuest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.Arrays;
import java.util.Objects;

public class ModQuests {

    public static final DeferredRegister<Quest> QUESTS_DEFERRED_REGISTER = DeferredRegister.create(ModRegistries.QUESTS, Main.MODID);
    public static final Quest[] SCHOOL = new Quest[] {};
    public static final Quest KILLHOLLOW = new KillHollowQuest();
    public static final Quest RESCUE_PLUSES = new RescuePlusesQuest();
    public static final Quest KILL_SPECIFIC_HOLLOW = new KillSpecificHollowQuest();
    public static final Quest KIDO_UNLOCK = new KidoUnlockQuest();
    public static final Quest BYAKURAI_QUEST = new ByakuraiQuest();
    public static final Quest TSUZURI_QUEST = new TsuzuriRaidenQuest();
    public static final Quest BAKUDO_UNLOCK = new BakudoUnlockQuest();
    public static final Quest HAINAWA_QUEST = new HainawaUnlockQuest();
    public static final Quest SEKI_QUEST = new SekiUnlockQuest();
    private static void registerQuests(Quest[] quests)
    {
        Arrays.stream(quests).filter(Objects::nonNull).forEach(abl -> registerQuest(abl));
    }

    public static void register(IEventBus eventBus)
    {
        registerQuest(KILLHOLLOW);
        registerQuest(RESCUE_PLUSES);
        registerQuest(KILL_SPECIFIC_HOLLOW);
        registerQuest(KIDO_UNLOCK);
        registerQuest(BYAKURAI_QUEST);
        registerQuest(TSUZURI_QUEST);
        registerQuest(BAKUDO_UNLOCK);
        registerQuest(HAINAWA_QUEST);
        registerQuest(SEKI_QUEST);
    }

    public static <T extends Quest> Quest registerQuest(Quest quest)
    {
        String resourceName = Beapi.getResourceName(quest.getTitle());
        BeRegistry.getLangMap().put("quest." + Main.MODID + "." + resourceName, quest.getTitle());

        final ResourceLocation key = new ResourceLocation(Main.MODID, resourceName);
        RegistryObject<Quest> ret = RegistryObject.of(key, ModRegistries.QUESTS);
        if (!QUESTS_DEFERRED_REGISTER.getEntries().contains(ret))
        {
            QUESTS_DEFERRED_REGISTER.register(resourceName, () -> quest);
        }
        return quest;
    }
}

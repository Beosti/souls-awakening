package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.ability.api.Ability;
import com.yuanno.soulsawakening.api.Beapi;
import com.yuanno.soulsawakening.quests.KidoUnlockQuest;
import com.yuanno.soulsawakening.quests.KillHollowQuest;
import com.yuanno.soulsawakening.quests.Quest;
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
    public static final Quest KIDO_UNLOCK = new KidoUnlockQuest();
    private static void registerQuests(Quest[] quests)
    {
        Arrays.stream(quests).filter(Objects::nonNull).forEach(abl -> registerQuest(abl));
    }

    public static void register(IEventBus eventBus)
    {
        registerQuest(KILLHOLLOW);
        registerQuest(KIDO_UNLOCK);
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

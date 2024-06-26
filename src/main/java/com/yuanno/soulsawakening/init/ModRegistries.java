package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModRegistries {
    static
    {
        make(new ResourceLocation(Main.MODID, "abilities"), Ability.class);
        make(new ResourceLocation(Main.MODID, "challenges"), ChallengeCore.class);
        make(new ResourceLocation(Main.MODID, "quests"), Quest.class);
    }

    public static final IForgeRegistry<Ability> ABILITIES = RegistryManager.ACTIVE.getRegistry(Ability.class);
    public static final IForgeRegistry<ChallengeCore<?>> CHALLENGES = RegistryManager.ACTIVE.getRegistry(ChallengeCore.class);
    public static final IForgeRegistry<Quest> QUESTS = RegistryManager.ACTIVE.getRegistry(Quest.class);


    public static <T extends IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type)
    {
        new RegistryBuilder<T>().setName(name).setType(type).setMaxID(Integer.MAX_VALUE - 1).create();
    }
}

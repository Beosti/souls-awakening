package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModChallenges {

    static {
        make(new ResourceLocation(Main.MODID, "challenges"), ChallengeCore.class);
    }

    public static final DeferredRegister<ChallengeCore<?>> CHALLENGES = DeferredRegister.create(ModRegistries.CHALLENGES, Main.MODID);
    public static <T extends IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type) {
        new RegistryBuilder<T>().setName(name).setType(type).setMaxID(Integer.MAX_VALUE - 1).create();
    }
}

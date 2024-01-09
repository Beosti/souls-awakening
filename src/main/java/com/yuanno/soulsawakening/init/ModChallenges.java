package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ModChallenges {


    public static final DeferredRegister<ChallengeCore<?>> CHALLENGES = DeferredRegister.create(ModRegistries.CHALLENGES, Main.MODID);


    public static void register(IEventBus bus) {
        CHALLENGES.register(bus);
    }
}

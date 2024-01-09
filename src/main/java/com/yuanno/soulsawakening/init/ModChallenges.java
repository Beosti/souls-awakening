package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.challenges.BanditChallenge;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

public class ModChallenges {

    public static final RegistryObject<ChallengeCore<BanditChallenge>> BANDIT = registerChallenge(BanditChallenge.INSTANCE);

    public static void register(IEventBus bus) {
        BeRegistry.CHALLENGES.register(bus);
    }
    public static <T extends Challenge> RegistryObject<ChallengeCore<T>> registerChallenge(ChallengeCore<T> challenge) {
        RegistryObject<ChallengeCore<T>> reg =  BeRegistry.CHALLENGES.register(challenge.getUnlocalizedTitle(), () -> challenge);
        return reg;
    }


}

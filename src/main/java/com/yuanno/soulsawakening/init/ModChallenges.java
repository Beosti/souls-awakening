package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.challenges.BasicShinigamiChallenge;
import com.yuanno.soulsawakening.challenges.SeatedOfficer20Challenge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class ModChallenges {

    public static final RegistryObject<ChallengeCore<BasicShinigamiChallenge>> BASIC_SHINIGAMI = registerChallenge(BasicShinigamiChallenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer20Challenge>> SEATED20_SHINIGAMI = registerChallenge(SeatedOfficer20Challenge.INSTANCE);

    public static void register(IEventBus bus) {
        BeRegistry.CHALLENGES.register(bus);
    }
    public static <T extends Challenge> RegistryObject<ChallengeCore<T>> registerChallenge(ChallengeCore<T> challenge) {
        RegistryObject<ChallengeCore<T>> reg =  BeRegistry.CHALLENGES.register(challenge.getUnlocalizedTitle(), () -> challenge);
        return reg;
    }


}

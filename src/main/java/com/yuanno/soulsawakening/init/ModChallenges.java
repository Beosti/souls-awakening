package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.challenges.shinigami.BasicShinigamiChallenge;
import com.yuanno.soulsawakening.challenges.shinigami.SeatedOfficer18Challenge;
import com.yuanno.soulsawakening.challenges.shinigami.SeatedOfficer19Challenge;
import com.yuanno.soulsawakening.challenges.shinigami.SeatedOfficer20Challenge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class ModChallenges {

    public static final RegistryObject<ChallengeCore<BasicShinigamiChallenge>> BASIC_SHINIGAMI = registerChallenge(BasicShinigamiChallenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer20Challenge>> SEATED20_SHINIGAMI = registerChallenge(SeatedOfficer20Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer19Challenge>> SEATED19_SHINIGAMI = registerChallenge(SeatedOfficer19Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer18Challenge>> SEATED18_SHINIGAMI = registerChallenge(SeatedOfficer18Challenge.INSTANCE);

    public static void register(IEventBus bus) {
        BeRegistry.CHALLENGES.register(bus);
    }
    public static <T extends Challenge> RegistryObject<ChallengeCore<T>> registerChallenge(ChallengeCore<T> challenge) {
        RegistryObject<ChallengeCore<T>> reg =  BeRegistry.CHALLENGES.register(challenge.getUnlocalizedTitle(), () -> challenge);
        return reg;
    }


}

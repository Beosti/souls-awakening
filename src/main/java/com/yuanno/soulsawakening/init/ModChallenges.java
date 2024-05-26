package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.api.challenges.Challenge;
import com.yuanno.soulsawakening.api.challenges.ChallengeCore;
import com.yuanno.soulsawakening.challenges.shinigami.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class ModChallenges {

    public static final RegistryObject<ChallengeCore<BasicShinigamiChallenge>> BASIC_SHINIGAMI = registerChallenge(BasicShinigamiChallenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer20Challenge>> SEATED20_SHINIGAMI = registerChallenge(SeatedOfficer20Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer19Challenge>> SEATED19_SHINIGAMI = registerChallenge(SeatedOfficer19Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer18Challenge>> SEATED18_SHINIGAMI = registerChallenge(SeatedOfficer18Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer17Challenge>> SEATED17_SHINIGAMI = registerChallenge(SeatedOfficer17Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer16Challenge>> SEATED16_SHINIGAMI = registerChallenge(SeatedOfficer16Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer15Challenge>> SEATED15_SHINIGAMI = registerChallenge(SeatedOfficer15Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer14Challenge>> SEATED14_SHINIGAMI = registerChallenge(SeatedOfficer14Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer13Challenge>> SEATED13_SHINIGAMI = registerChallenge(SeatedOfficer13Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer12Challenge>> SEATED12_SHINIGAMI = registerChallenge(SeatedOfficer12Challenge.INSTANCE);
    public static final RegistryObject<ChallengeCore<SeatedOfficer11Challenge>> SEATED11_SHINIGAMI = registerChallenge(SeatedOfficer11Challenge.INSTANCE);

    public static void register(IEventBus bus) {
        BeRegistry.CHALLENGES.register(bus);
    }
    public static <T extends Challenge> RegistryObject<ChallengeCore<T>> registerChallenge(ChallengeCore<T> challenge) {
        RegistryObject<ChallengeCore<T>> reg =  BeRegistry.CHALLENGES.register(challenge.getUnlocalizedTitle(), () -> challenge);
        return reg;
    }


}

package com.yuanno.soulsawakening.challenges.shinigami;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer18;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer19;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraftforge.fml.RegistryObject;

public class SeatedOfficer18Challenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.seated18shinigami", "Seated officer 18");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.seated18shinigami", "Defeat a 18th seated officer");




    public static final ChallengeCore<SeatedOfficer18Challenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", SeatedOfficer18Challenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArenaSeatedOfficer18.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addChallenge(() -> {
                        ChallengeCore challengeCore = ModChallenges.SEATED17_SHINIGAMI.get();
                        return challengeCore;
                    })
                    .addZanpakutoStyle("back_horizontal")
                    .setKan(24))
            .setSecondReward(new ChallengeReward().setKan(14))
            .build();
    public SeatedOfficer18Challenge(ChallengeCore core) {
        super(core);
    }
}

package com.yuanno.soulsawakening.challenges.shinigami;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer19;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer20;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraftforge.fml.RegistryObject;

public class SeatedOfficer19Challenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.seated19shinigami", "Seated officer 19");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.seated19shinigami", "Defeat a 19th seated officer");



    public static final ChallengeCore<SeatedOfficer19Challenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", SeatedOfficer19Challenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArenaSeatedOfficer19.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setReward(new ChallengeReward()
                    .addChallenge(() -> {
                        ChallengeCore challengeCore = ModChallenges.SEATED18_SHINIGAMI.get();
                        return challengeCore;
                    })
                    .addZanpakutoStyle("basic_right")
                    .setKan(18))
            .setSecondReward(new ChallengeReward().setKan(8))
            .build();
    public SeatedOfficer19Challenge(ChallengeCore core) {
        super(core);
    }
}

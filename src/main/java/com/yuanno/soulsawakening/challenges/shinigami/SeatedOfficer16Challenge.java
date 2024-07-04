package com.yuanno.soulsawakening.challenges.shinigami;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer16;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer18;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraftforge.fml.RegistryObject;

public class SeatedOfficer16Challenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.seated16shinigami", "Seated officer 16");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.seated16shinigami", "Defeat a 16th seated officer");




    public static final ChallengeCore<SeatedOfficer16Challenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", SeatedOfficer16Challenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArenaSeatedOfficer16.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addChallenge(() -> {
                        ChallengeCore challengeCore = ModChallenges.SEATED15_SHINIGAMI.get();
                        return challengeCore;
                    })
                    .addZanpakutoStyle("back_horizontal_diagonal_left")
                    .setKan(34))
            .setSecondReward(new ChallengeReward().setKan(24))
            .build();
    public SeatedOfficer16Challenge(ChallengeCore core) {
        super(core);
    }
}

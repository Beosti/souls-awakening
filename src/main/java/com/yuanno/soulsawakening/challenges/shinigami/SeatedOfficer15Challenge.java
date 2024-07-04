package com.yuanno.soulsawakening.challenges.shinigami;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer15;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer18;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraftforge.fml.RegistryObject;

public class SeatedOfficer15Challenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.seated15shinigami", "Seated officer 15");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.seated15shinigami", "Defeat a 15th seated officer");




    public static final ChallengeCore<SeatedOfficer15Challenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", SeatedOfficer15Challenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArenaSeatedOfficer15.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addChallenge(() -> {
                        ChallengeCore challengeCore = ModChallenges.SEATED14_SHINIGAMI.get();
                        return challengeCore;
                    })
                    .addZanpakutoStyle("back_horizontal_diagonal_right")
                    .setKan(40))
            .setSecondReward(new ChallengeReward().setKan(30))
            .build();
    public SeatedOfficer15Challenge(ChallengeCore core) {
        super(core);
    }
}

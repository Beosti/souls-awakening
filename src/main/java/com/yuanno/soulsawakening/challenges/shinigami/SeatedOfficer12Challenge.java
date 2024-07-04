package com.yuanno.soulsawakening.challenges.shinigami;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer12;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer18;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraftforge.fml.RegistryObject;

public class SeatedOfficer12Challenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.seated12shinigami", "Seated officer 12");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.seated12shinigami", "Defeat a 12th seated officer");




    public static final ChallengeCore<SeatedOfficer12Challenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", SeatedOfficer12Challenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArenaSeatedOfficer12.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addChallenge(() -> {
                        ChallengeCore challengeCore = ModChallenges.SEATED11_SHINIGAMI.get();
                        return challengeCore;
                    })
                    .addZanpakutoStyle("back_vertical_diagonal_right")
                    .setKan(64))
            .setSecondReward(new ChallengeReward().setKan(48))
            .build();
    public SeatedOfficer12Challenge(ChallengeCore core) {
        super(core);
    }
}

package com.yuanno.soulsawakening.challenges;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.init.ModEntities;
import net.minecraftforge.fml.RegistryObject;

public class BanditChallenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.bandit", "Bandit");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.bandit.objective", "Defeat bandit");




    public static final ChallengeCore<BanditChallenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", BanditChallenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArena.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setOrder(0)
            //.setReward(new ChallengeReward().setDoriki(0))
            .build();
    public BanditChallenge(ChallengeCore core) {
        super(core);
    }
}

package com.yuanno.soulsawakening.challenges;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArenaSeatedOfficer20;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;

public class SeatedOfficer20Challenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.seated20shinigami", "Seated officer 20");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.seated20shinigami", "Defeat a 20th seated officer");




    public static final ChallengeCore<SeatedOfficer20Challenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", SeatedOfficer20Challenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArenaSeatedOfficer20.INSTANCE)
            .setTargetShowcase(new RegistryObject[]{ModEntities.SHINIGAMI})
            .setTimeLimit(10)
            .setOrder(0)
            .setReward(new ChallengeReward()
                    .addChallenge(() -> {
                        ChallengeCore challengeCore = ModChallenges.SEATED20_SHINIGAMI.get();
                        return challengeCore;
                    })
                    .addItem(() -> {
                        ItemStack test = new ItemStack(ModItems.REISHI.get());
                        test.grow(5);
                        return test;
                    }))
            .setSecondReward(new ChallengeReward().addItem(() -> {
                ItemStack test = new ItemStack(ModItems.REISHI.get());
                test.grow(3);
                return test;
            }))
            .build();
    public SeatedOfficer20Challenge(ChallengeCore core) {
        super(core);
    }
}

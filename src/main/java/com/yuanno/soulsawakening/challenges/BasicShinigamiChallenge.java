package com.yuanno.soulsawakening.challenges;

import com.yuanno.soulsawakening.BeRegistry;
import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.api.challenges.*;
import com.yuanno.soulsawakening.challenges.arena.GlowstoneArena;
import com.yuanno.soulsawakening.init.ModChallenges;
import com.yuanno.soulsawakening.init.ModEntities;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

public class BasicShinigamiChallenge extends Challenge {
    private static final String TITLE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.shinigami", "Shinigami");
    public static final String OBJECTIVE = BeRegistry.registerName("challenge." + Main.MODID + ".begin.objective.shinigami", "Defeat a basic  Shinigami");




    public static final ChallengeCore<BasicShinigamiChallenge> INSTANCE = new ChallengeCore.Builder(TITLE, OBJECTIVE, "category", BasicShinigamiChallenge::new)
            .setDifficulty(ChallengeDifficulty.STANDARD)
            .setDifficultyStars(4)
            .addArena(ArenaStyle.BOX, GlowstoneArena.INSTANCE)
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
                        test.grow(4);
                        return test;
                    }))
            .setSecondReward(new ChallengeReward().addItem(() -> {
                ItemStack test = new ItemStack(ModItems.REISHI.get());
                test.grow(1);
                return test;
            }))
            .build();
    public BasicShinigamiChallenge(ChallengeCore core) {
        super(core);
    }
}

package com.yuanno.soulsawakening.challenges.arena;

import com.yuanno.soulsawakening.api.challenges.InProgressChallenge;
import com.yuanno.soulsawakening.entity.ChallengeShinigamiEntity;
import com.yuanno.soulsawakening.init.ModEntities;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class GlowstoneArenaSeatedOfficer12 extends GlowstoneArena {
    public static final GlowstoneArenaSeatedOfficer12 INSTANCE = new GlowstoneArenaSeatedOfficer12();

    public GlowstoneArenaSeatedOfficer12()
    {
        super();
    }

    @Override
    public Set<LivingEntity> spawnEnemies(InProgressChallenge challenge) {

        Set<LivingEntity> set = new HashSet<>();

        ChallengeShinigamiEntity boss = new ChallengeShinigamiEntity(ModEntities.CHALLENGE_SHINIGAMI.get(), challenge.getShard());
        ItemStack swordStack = new ItemStack(ModItems.ZANPAKUTO.get());
        swordStack.getTag().putString("owner", boss.getDisplayName().getString());
        boss.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(22);
        boss.getAttribute(Attributes.MAX_HEALTH).setBaseValue(42);
        boss.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2982);

        boss.setItemSlot(EquipmentSlotType.MAINHAND, swordStack);
        boss.forcedLoading = true;
        challenge.getShard().addFreshEntity(boss);
        boss.teleportTo(challenge.getArenaPos().getX() - 15, challenge.getArenaPos().getY() - 23, challenge.getArenaPos().getZ() - 15);
        boss.setYBodyRot(-45);
        boss.setTarget(challenge.getOwner());
        //GoalUtil.lookAtEntity(boss, challenge.getOwner());
        set.add(boss);

        return set;
    }
}

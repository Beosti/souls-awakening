package com.yuanno.soulsawakening.entity;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Random;

public class PlusEntity extends CreatureEntity {

    public String[] options = {"cool", "magma", "receptionist", "vex"};
    public String constantSkin;
    public PlusEntity(EntityType type, World world)
    {
        super(type, world);
        Random random = new Random();
        int randomIndex = random.nextInt(options.length);
        this.constantSkin = options[randomIndex];
    }
    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));

    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.FOLLOW_RANGE, 100)
                .add(Attributes.MOVEMENT_SPEED, 3)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }
    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        if (hand != Hand.MAIN_HAND)
            return ActionResultType.PASS;
        if (!player.level.isClientSide)
        {
            this.lookAt(player, 1, 1);
            IEntityStats entityStats = EntityStatsCapability.get(player);
            if (entityStats.getRace().equals(ModValues.HOLLOW))
            {
                String killerString = selectRandomStringHollow();
                player.sendMessage(new StringTextComponent(killerString), Util.NIL_UUID);
                entityStats.alterHollowPoints(1);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);

                this.kill();
            }
            else if (entityStats.getRace().equals(ModValues.FULLBRINGER) || entityStats.getRace().equals(ModValues.SHINIGAMI)
                    && player.getMainHandItem().getItem().asItem().equals(ModItems.ZANPAKUTO.get().getItem().asItem()))
            {
                String saviorString = selectRandomStringShinigami();
                player.sendMessage(new StringTextComponent(saviorString), Util.NIL_UUID);
                entityStats.alterClassPoints(1);
                PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
                this.remove();
            }
        }
        return ActionResultType.PASS;
    }

    public static String selectRandomStringShinigami() {
        // Define the strings
        String[] strings = {
                "I'll see you on the other side!",
                "Thank you!",
                "Goodbye...",
                "Will I see mom there?",
                "How do I become like you?"
        };

        // Use the Random class to generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(strings.length);

        // Return the selected string
        return strings[randomIndex];
    }

    public static String selectRandomStringHollow() {
        // Define the strings
        String[] strings = {
                "please don't kill me!",
                "no please!",
                "where am I, please don't!",
                "I have money, I'll give you anything!",
                "monster!"
        };

        // Use the Random class to generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(strings.length);

        // Return the selected string
        return strings[randomIndex];
    }
}

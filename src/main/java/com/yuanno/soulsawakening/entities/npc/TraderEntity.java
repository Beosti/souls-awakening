package com.yuanno.soulsawakening.entities.npc;

import com.yuanno.soulsawakening.entity.goal.ImprovedMeleeAttackGoal;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SOpenTradingScreenPacket;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TraderEntity extends CreatureEntity {

    public TraderEntity(EntityType type, World world)
    {
        super(type, world);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();



        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(4, new ImprovedMeleeAttackGoal(this, 1, true));
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand)
    {
        if (hand != Hand.MAIN_HAND)
            return ActionResultType.PASS;
        if (!player.level.isClientSide) {
            this.lookAt(player, 1, 1);
            PacketHandler.sendTo(new SOpenTradingScreenPacket(), player);
            ModAdvancements.CAPITALISM.trigger((ServerPlayerEntity) player);
        }
        return ActionResultType.PASS;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 100)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MAX_HEALTH, 500)
                .add(Attributes.FOLLOW_RANGE, 100)
                .add(Attributes.MOVEMENT_SPEED, 0.31)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }
}

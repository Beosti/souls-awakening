package com.yuanno.soulsawakening.entity;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class CentipedeEntity extends HollowEntity {

    public CentipedeEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        this.element = ModValues.DARK;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();

        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

    }

    @Override
    public boolean doHurtTarget(Entity p_70652_1_) {
        boolean flag = super.doHurtTarget(p_70652_1_);
        if (flag && this.getMainHandItem().isEmpty() && p_70652_1_ instanceof LivingEntity) {
            float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.POISON, 140 * (int)f));
        }

        return flag;
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.285)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }


    @Override
    public boolean removeWhenFarAway(double d)
    {
        return false;
    }

    @Override
    public float getBrightness() {
        return 2.0F;
    }

    @Override
    public boolean isSensitiveToWater() {
        return false;
    }



    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
    {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
        return spawnData;

    }


}

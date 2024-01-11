package com.yuanno.soulsawakening.entity;

import com.google.common.base.Predicates;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.hollow.HollowEntity;
import com.yuanno.soulsawakening.entity.hollow.JetEntity;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ChallengeShinigamiEntity extends CreatureEntity {

    public String[] options = {"shinigami_normal"};
    public String constantSkin;
    public ChallengeShinigamiEntity(EntityType type, World world)
    {
        super(type, world);
        this.constantSkin = "shinigami_normal";
    }
    @Override
    protected void registerGoals() // TODO improve this for the challenge
    {
        super.registerGoals();

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        this.goalSelector.addGoal(7, new ImprovedMeleeAttackGoal(this, 1, true));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        //this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 4));

    }

    public static Predicate<Entity> getEnemyFactions(LivingEntity entity)
    {
        if (entity == null)
        {
            return Predicates.alwaysTrue();
        }

        return target -> {

            if (!(target instanceof LivingEntity))
                return false;

            if (entity == target) {
                return false;
            }

            if(target.getVehicle() != null && target.getVehicle().equals(entity)) {
                return false;
            }

            /*
            if(isTamedBy(entity).test(target)) {
                return false;
            }

             */

            boolean isSpectating = !EntityPredicates.NO_SPECTATORS.test(target);
            if (isSpectating) {
                return false;
            }

            IEntityStats entityStats = EntityStatsCapability.get((LivingEntity) target);
            if (entityStats.getRace().equals(ModValues.HOLLOW))
                return true;
            else
                return false;
        };
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.FOLLOW_RANGE, 100)
                .add(Attributes.MOVEMENT_SPEED, 0.28)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }

    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
    {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);

        ItemStack swordStack = new ItemStack(ModItems.ZANPAKUTO.get());
        swordStack.getTag().putString("owner", this.getDisplayName().getString());

        this.setItemSlot(EquipmentSlotType.MAINHAND, swordStack);
        int randomIndex = random.nextInt(options.length);
        this.constantSkin = options[randomIndex];

        return spawnData;
    }
}

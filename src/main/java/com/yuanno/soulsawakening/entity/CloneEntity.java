package com.yuanno.soulsawakening.entity;

import com.google.common.base.Predicates;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import com.yuanno.soulsawakening.entity.goal.ImprovedMeleeAttackGoal;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModConfig;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class CloneEntity extends CreatureEntity {

    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.defineId(CloneEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<Boolean> IS_TEXTURED = EntityDataManager.defineId(CloneEntity.class, DataSerializers.BOOLEAN);
    private int maxAliveTicks = 200;

    public CloneEntity(EntityType type, World world)
    {
        super(type, world);
    }

    @Override
    protected void registerGoals()
    {

        Predicate<Entity> factionScope = getEnemyFactions(this);


        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, true, factionScope));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, HollowEntity.class, 10, true, true, factionScope));

        this.goalSelector.addGoal(4, new ImprovedMeleeAttackGoal(this, 1, true));

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

            boolean isSpectating = !EntityPredicates.NO_SPECTATORS.test(target);
            if (isSpectating) {
                return false;
            }

            if (entity instanceof CloneEntity)
            {
                CloneEntity cloneEntity = (CloneEntity) entity;
                if (cloneEntity.getOwnerUUID().equals(target.getUUID()))
                    return false;
                else
                    return true;
            }
            else
                return false;
        };
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(OWNER, Optional.empty());
        this.entityData.define(IS_TEXTURED, false);
    }

    @Override
    public void tick()
    {
        super.tick();
        if (!this.level.isClientSide && this.getOwner() == null)
        {
            this.remove();
            return;
        }

        this.setLastHurtByMob(this.getOwner());

        if (this.tickCount >= this.maxAliveTicks)
            this.remove();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound)
    {
        super.addAdditionalSaveData(compound);
        if (this.entityData.get(OWNER) != null)
            compound.putString("OwnerUUID", this.entityData.get(OWNER).get().toString());
        compound.putBoolean("isTextured", this.entityData.get(IS_TEXTURED));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound)
    {
        super.readAdditionalSaveData(compound);
        this.entityData.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
        this.entityData.set(IS_TEXTURED, compound.getBoolean("isTextured"));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ARMOR, 0)
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.FOLLOW_RANGE, 30)
                .add(Attributes.MOVEMENT_SPEED, 0.305)
                .add(ModAttributes.ATTACK_RANGE.get(), 0.5)
                .add(ModAttributes.FALL_RESISTANCE.get(), 0);

    }

    public void setOwner(UUID uuid)
    {
        this.entityData.set(OWNER, Optional.of(uuid));
    }

    @Nullable
    public UUID getOwnerUUID()
    {
        return this.getEntityData().get(OWNER).orElse(null);
    }

    @Nullable
    public PlayerEntity getOwner()
    {
        UUID id = this.getOwnerUUID();
        if(id == null)
            return null;
        return this.level.getPlayerByUUID(id);
    }

    public void setUseOwnerTexture()
    {
        this.entityData.set(IS_TEXTURED, true);
    }

    public boolean isUsingOwnerTexture()
    {
        return this.getEntityData().get(IS_TEXTURED);
    }

    public void setMaxAliveTicks(int ticks)
    {
        this.maxAliveTicks = ticks;
    }
}
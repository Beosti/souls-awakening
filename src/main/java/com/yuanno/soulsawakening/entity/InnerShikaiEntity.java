package com.yuanno.soulsawakening.entity;

import com.yuanno.soulsawakening.entity.goal.ImprovedMeleeAttackGoal;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class InnerShikaiEntity extends CreatureEntity {

    private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.defineId(InnerShikaiEntity.class, DataSerializers.OPTIONAL_UUID);
    private static final DataParameter<Boolean> IS_TEXTURED = EntityDataManager.defineId(InnerShikaiEntity.class, DataSerializers.BOOLEAN);


    public InnerShikaiEntity(EntityType type, World world)
    {
        super(type, world);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();

        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

        this.goalSelector.addGoal(4, new ImprovedMeleeAttackGoal(this, 1, true));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(OWNER, Optional.empty());
        this.entityData.define(IS_TEXTURED, false);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 7)
                .add(Attributes.ARMOR, 20)
                .add(Attributes.MAX_HEALTH, 30)
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

        return spawnData;
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

}

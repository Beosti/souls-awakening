package com.yuanno.soulsawakening.projectiles;

import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import com.yuanno.soulsawakening.events.projectiles.ProjectileShootEvent;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkDirection;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbilityProjectileEntity extends ThrowableEntity implements IEntityAdditionalSpawnData {

    private int life = 64;
    private int maxLife = 64;
    private double collisionSizeX = 0;
    private double collisionSizeY = 0;
    private double collisionSizeZ = 0;
    private float damage = 0.1F;
    private float gravity = 0.0001F;
    private boolean canPassThroughBlocks = false;
    private boolean canPassThroughEntities = false;
    private boolean changeHurtTime = false;
    private int hurtTime = 10;
    boolean entityDamaged = false;
    private List<Integer> targets = new ArrayList<>();
    private int targetResetTime = 20;
    private SourceElement sourceElement = SourceElement.NONE;

    private static final DataParameter<Integer> OWNER = EntityDataManager.defineId(AbilityProjectileEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> LIFE = EntityDataManager.defineId(AbilityProjectileEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> IS_GLOWING = EntityDataManager.defineId(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);


    public AbilityProjectileEntity(EntityType type, World world)
    {
        super(type, world);
    }

    public AbilityProjectileEntity(EntityType type, World world, double x, double y, double z)
    {
        super(type, x, y, z, world);
    }

    public AbilityProjectileEntity(EntityType type, World world, LivingEntity thrower)
    {
        super(type, thrower, world);
    }

    public void setThrower(LivingEntity entity)
    {
        this.entityData.set(OWNER, entity.getId());
        this.setOwner(entity);
    }

    public void clearTargets()
    {
        this.targets.clear();
    }
    @Nullable
    public LivingEntity getThrower()
    {
        if(this.getOwner() instanceof LivingEntity)
            return (LivingEntity) this.getOwner();
        else
            return null;
    }
    @Override
    public void shootFromRotation(Entity thrower, float yRotIn, float xRotIn, float pitchOffset, float velocity, float inaccuracy)
    {
        ProjectileShootEvent event = new ProjectileShootEvent(this, velocity, inaccuracy);
        if (MinecraftForge.EVENT_BUS.post(event))
            return;
        this.clearTargets();
        super.shootFromRotation(thrower, yRotIn, xRotIn, pitchOffset, velocity, inaccuracy);
    }
    @Nullable
    @Override
    public Entity getOwner()
    {
        return this.getEntityData().get(OWNER) != null && this.level.getEntity(this.getEntityData().get(OWNER)) instanceof LivingEntity ? (LivingEntity) this.level.getEntity(this.getEntityData().get(OWNER)) : super.getOwner();
    }

    @Override
    public void defineSynchedData()
    {
        this.entityData.define(LIFE, 64);
        this.entityData.define(OWNER, -1);
        this.entityData.define(IS_GLOWING, false);
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer)
    {
        buffer.writeDouble(this.collisionSizeX);
        buffer.writeDouble(this.collisionSizeY);
        buffer.writeDouble(this.collisionSizeZ);
        buffer.writeInt(this.sourceElement.ordinal());
    }

    @Override
    public void readSpawnData(PacketBuffer buffer)
    {
        this.collisionSizeX = buffer.readDouble();
        this.collisionSizeY = buffer.readDouble();
        this.collisionSizeZ = buffer.readDouble();
        this.sourceElement = SourceElement.values()[buffer.readInt()];
    }

    public int getLife()
    {
        return this.life;
    }

    public int getMaxLife()
    {
        return this.maxLife;
    }

    public void setMaxLife(int life)
    {
        this.maxLife = life;
        this.life = this.maxLife;
    }

    public void setLife(int life)
    {
        this.life = life;
    }

    public double getCollisionSizeX()
    {
        return this.collisionSizeX;
    }

    public double getCollisionSizeY()
    {
        return this.collisionSizeY;
    }

    public double getCollisionSizeZ()
    {
        return this.collisionSizeZ;
    }

    public void setCollisionSize(double val)
    {
        this.collisionSizeX = val;
        this.collisionSizeY = val;
        this.collisionSizeZ = val;
    }

    public void setCollisionSize(double x, double y, double z)
    {
        this.collisionSizeX = x;
        this.collisionSizeY = y;
        this.collisionSizeZ = z;
    }
}

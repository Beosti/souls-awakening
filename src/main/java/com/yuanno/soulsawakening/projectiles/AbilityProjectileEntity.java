package com.yuanno.soulsawakening.projectiles;

import com.yuanno.soulsawakening.api.SourceElement;
import com.yuanno.soulsawakening.api.SourceType;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbilityProjectileEntity extends ThrowableEntity implements IEntityAdditionalSpawnData {

    private int maxLife = 64;
    private int knockbackStrength = 0;
    private double collisionSizeX = 0;
    private double collisionSizeY = 0;
    private double collisionSizeZ = 0;
    private float damage = 0.1F;
    private float gravity = 0.0001F;
    private boolean canPassThroughBlocks = false;
    private boolean canPassThroughEntities = false;
    private boolean canGetStuckInGround = false;
    protected boolean stuckInGround = false;
    private boolean changeHurtTime = false;
    private float armorPiercing = 0.0f;
    private boolean canHurtThrower = false;
    private boolean isFake = false;
    // For reference default hurt time is 20
    private int hurtTime = 10;
    boolean entityDamaged = false;
    boolean applyOnlyOnce = true;
    private List<Integer> targets = new ArrayList<>();
    private int targetResetTime = 20;

    private SourceType[] sourceTypes = new SourceType[] {};
    private SourceElement sourceElement = SourceElement.NONE;

    private static final DataParameter<Integer> OWNER = EntityDataManager.defineId(AbilityProjectileEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> LIFE = EntityDataManager.defineId(AbilityProjectileEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> IS_GLOWING = EntityDataManager.defineId(AbilityProjectileEntity.class, DataSerializers.BOOLEAN);

    // Setting the defaults so that no crash occurs and so they will be null safe.
    public IOnEntityImpact onEntityImpactEvent = (hitEntity) ->
    {
        if(!this.targets.contains(hitEntity.getId()))
            this.onBlockImpactEvent.onImpact(hitEntity.blockPosition());
    };

    private DamageSource source;
    private static final Block[] NON_SOLID_BLOCKS = new Block[] { Blocks.SUNFLOWER, Blocks.GRASS, Blocks.TALL_GRASS, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS, Blocks.VINE, Blocks.REDSTONE_WIRE, Blocks.DEAD_BUSH, Blocks.ROSE_BUSH, ModBlocks.OPE.get() };

    public IOnBlockImpact onBlockImpactEvent = (hit) -> {};
    public IOnTick onTickEvent = () -> {};

    public AbilityProjectileEntity(EntityType entityType, World world)
    {
        super(entityType, world);
    }
    public AbilityProjectileEntity(EntityType type, World world, double x, double y, double z)
    {
        super(type, x, y, z, world);
    }

    public AbilityProjectileEntity(EntityType entityType, World world, LivingEntity thrower)
    {
        super(entityType, thrower, world);
        this.maxLife = this.getLife();
        this.damage = 0.1f;
        this.setThrower(thrower);
    }

    public void setThrower(LivingEntity entity)
    {
        this.entityData.set(OWNER, entity.getId());
        this.setOwner(entity);
    }

    public int getLife()
    {
        return this.entityData.get(LIFE);
    }

    public interface IOnTick extends Serializable
    {
        void onTick();
    }

    public interface IOnEntityImpact extends Serializable
    {
        void onImpact(LivingEntity hitEntity);
    }

    public interface IOnBlockImpact extends Serializable
    {
        void onImpact(BlockPos hitPos);
    }
}

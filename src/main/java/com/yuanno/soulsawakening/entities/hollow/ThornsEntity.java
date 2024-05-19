package com.yuanno.soulsawakening.entities.hollow;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.PlusEntity;
import com.yuanno.soulsawakening.entity.goal.ImprovedMeleeAttackGoal;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class ThornsEntity extends HollowEntity implements IBleach {
    String element;

    public ThornsEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        //this.moveControl = new ThornsEntity.MoveHelperController(this);
        //this.lookControl = new ThornsEntity.LookHelperController(this);
        this.element = ModValues.FIRE;
    }
    public static AttributeModifierMap.MutableAttribute setCustomAttributes()
    {
        return MobEntity.createMobAttributes()
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.285)
                .add(ModAttributes.ATTACK_RANGE.get(), 0.5)
                .add(ModAttributes.FALL_RESISTANCE.get(), 50);

    }
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlusEntity.class, false));
        this.goalSelector.addGoal(4, new ImprovedMeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
    }
    public boolean hurt(DamageSource damageSource, float p_70097_2_) {
        boolean flag = super.hurt(damageSource, p_70097_2_);
        if (damageSource.getDirectEntity() != null)
        {
            if (damageSource.getDirectEntity() instanceof LivingEntity)
            {
                LivingEntity livingEntity = (LivingEntity) damageSource.getDirectEntity();
                livingEntity.hurt(damageSource, 2);
            }
        }

        return flag;
    }
    public void die(DamageSource source)
    {
        super.die(source);

        if (!(source.getEntity() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) source.getEntity();
        if (!(player.getMainHandItem().getItem().asItem() instanceof ZanpakutoItem))
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.SHINIGAMI) && !entityStats.getRace().equals(ModValues.FULLBRINGER))
            return;
        ItemStack zanpakutoItem = player.getMainHandItem();
        int chancePercentage = 5;

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        if (randomNumber <= chancePercentage) {
            addPoint(zanpakutoItem, this.element);
        }
    }

    void addPoint(ItemStack zanpakutoItem, String element)
    {
        CompoundNBT tagCompound = zanpakutoItem.getTag();
        int elementalPoints = tagCompound.getInt("element");
        if (elementalPoints >= 5)
            return;
        int thunderPoint = tagCompound.getInt(element);
        tagCompound.putInt(element, thunderPoint + 1);
        tagCompound.putInt("element", elementalPoints + 1);
        zanpakutoItem.setTag(tagCompound);
    }

    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
    {
        spawnData = super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
        EntityStatsCapability.get(this).setRace(ModValues.HOLLOW);
        return spawnData;

    }
    @Override
    public String getRank() {
        return ModValues.BASE;
    }
    @Override
    public String getRace()
    {
        return ModValues.HOLLOW;
    }
}

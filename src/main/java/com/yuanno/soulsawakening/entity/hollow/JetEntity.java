package com.yuanno.soulsawakening.entity.hollow;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entity.PlusEntity;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class JetEntity extends FlyingEntity implements IMob {
    String element;
    private Vector3d moveTargetPoint = Vector3d.ZERO;
    private BlockPos anchorPoint = BlockPos.ZERO;
    private JetEntity.AttackPhase attackPhase = JetEntity.AttackPhase.CIRCLE;


    public JetEntity(EntityType<? extends FlyingEntity> p_i48575_1_, World p_i48575_2_) {
        super(p_i48575_1_, p_i48575_2_);
        this.moveControl = new JetEntity.MoveHelperController(this);
        this.lookControl = new JetEntity.LookHelperController(this);
        this.element = ModValues.WIND;
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
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new JetEntity.PickAttackGoal());
        this.goalSelector.addGoal(2, new JetEntity.SweepAttackGoal());
        this.goalSelector.addGoal(3, new JetEntity.OrbitPointGoal());
        this.targetSelector.addGoal(1, new JetEntity.AttackPlayerGoal());
    }
    
    class LookHelperController extends LookController {
        public LookHelperController(MobEntity p_i48802_2_) {
            super(p_i48802_2_);
        }

        public void tick() {
        }
    }

    static enum AttackPhase {
        CIRCLE,
        SWOOP;
    }

    class AttackPlayerGoal extends Goal {
        private final EntityPredicate attackTargeting = (new EntityPredicate()).range(64.0D);
        private int nextScanTick = 20;

        private AttackPlayerGoal() {
        }

        public boolean canUse() {
            if (this.nextScanTick > 0) {
                --this.nextScanTick;
                return false;
            } else {
                this.nextScanTick = 60;
                List<PlayerEntity> list = JetEntity.this.level.getNearbyPlayers(this.attackTargeting, JetEntity.this, JetEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
                List<PlusEntity> listPlus = JetEntity.this.level.getNearbyEntities(PlusEntity.class, this.attackTargeting, JetEntity.this, JetEntity.this.getBoundingBox().inflate(16.0D, 64.0D, 16.0D));
                if (!list.isEmpty()) {
                    list.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

                    for(PlayerEntity playerentity : list) {
                        if (JetEntity.this.canAttack(playerentity, EntityPredicate.DEFAULT)) {
                            JetEntity.this.setTarget(playerentity);
                            return true;
                        }
                    }
                }
                else if (!listPlus.isEmpty())
                {
                    listPlus.sort(Comparator.<Entity, Double>comparing(Entity::getY).reversed());

                    for(PlusEntity plusEntity : listPlus) {
                        if (JetEntity.this.canAttack(plusEntity, EntityPredicate.DEFAULT)) {
                            JetEntity.this.setTarget(plusEntity);
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = JetEntity.this.getTarget();
            return livingentity != null ? JetEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT) : false;
        }
    }
    
    abstract class MoveGoal extends Goal {
        public MoveGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return JetEntity.this.moveTargetPoint.distanceToSqr(JetEntity.this.getX(), JetEntity.this.getY(), JetEntity.this.getZ()) < 4.0D;
        }
    }
    
    class MoveHelperController extends MovementController {
        private float speed = 0.1F;

        public MoveHelperController(MobEntity p_i48801_2_) {
            super(p_i48801_2_);
        }

        public void tick() {
            if (JetEntity.this.horizontalCollision) {
                JetEntity.this.yRot += 180.0F;
                this.speed = 0.1F;
            }

            float f = (float)(JetEntity.this.moveTargetPoint.x - JetEntity.this.getX());
            float f1 = (float)(JetEntity.this.moveTargetPoint.y - JetEntity.this.getY());
            float f2 = (float)(JetEntity.this.moveTargetPoint.z - JetEntity.this.getZ());
            double d0 = (double) MathHelper.sqrt(f * f + f2 * f2);
            double d1 = 1.0D - (double)MathHelper.abs(f1 * 0.7F) / d0;
            f = (float)((double)f * d1);
            f2 = (float)((double)f2 * d1);
            d0 = (double)MathHelper.sqrt(f * f + f2 * f2);
            double d2 = (double)MathHelper.sqrt(f * f + f2 * f2 + f1 * f1);
            float f3 = JetEntity.this.yRot;
            float f4 = (float)MathHelper.atan2((double)f2, (double)f);
            float f5 = MathHelper.wrapDegrees(JetEntity.this.yRot + 90.0F);
            float f6 = MathHelper.wrapDegrees(f4 * (180F / (float)Math.PI));
            JetEntity.this.yRot = MathHelper.approachDegrees(f5, f6, 4.0F) - 90.0F;
            JetEntity.this.yBodyRot = JetEntity.this.yRot;
            if (MathHelper.degreesDifferenceAbs(f3, JetEntity.this.yRot) < 3.0F) {
                this.speed = MathHelper.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
            } else {
                this.speed = MathHelper.approach(this.speed, 0.2F, 0.025F);
            }

            float f7 = (float)(-(MathHelper.atan2((double)(-f1), d0) * (double)(180F / (float)Math.PI)));
            JetEntity.this.xRot = f7;
            float f8 = JetEntity.this.yRot + 90.0F;
            double d3 = (double)(this.speed * MathHelper.cos(f8 * ((float)Math.PI / 180F))) * Math.abs((double)f / d2);
            double d4 = (double)(this.speed * MathHelper.sin(f8 * ((float)Math.PI / 180F))) * Math.abs((double)f2 / d2);
            double d5 = (double)(this.speed * MathHelper.sin(f7 * ((float)Math.PI / 180F))) * Math.abs((double)f1 / d2);
            Vector3d vector3d = JetEntity.this.getDeltaMovement();
            JetEntity.this.setDeltaMovement(vector3d.add((new Vector3d(d3, d5, d4)).subtract(vector3d).scale(0.2D)));
        }
    }

    class OrbitPointGoal extends JetEntity.MoveGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        private OrbitPointGoal() {
        }

        public boolean canUse() {
            return JetEntity.this.getTarget() == null || JetEntity.this.attackPhase == JetEntity.AttackPhase.CIRCLE;
        }

        public void start() {
            this.distance = 5.0F + JetEntity.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + JetEntity.this.random.nextFloat() * 9.0F;
            this.clockwise = JetEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        public void tick() {
            if (JetEntity.this.random.nextInt(350) == 0) {
                this.height = -4.0F + JetEntity.this.random.nextFloat() * 9.0F;
            }

            if (JetEntity.this.random.nextInt(250) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (JetEntity.this.random.nextInt(450) == 0) {
                this.angle = JetEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (JetEntity.this.moveTargetPoint.y < JetEntity.this.getY() && !JetEntity.this.level.isEmptyBlock(JetEntity.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (JetEntity.this.moveTargetPoint.y > JetEntity.this.getY() && !JetEntity.this.level.isEmptyBlock(JetEntity.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(JetEntity.this.anchorPoint)) {
                JetEntity.this.anchorPoint = JetEntity.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
            JetEntity.this.moveTargetPoint = Vector3d.atLowerCornerOf(JetEntity.this.anchorPoint).add((double)(this.distance * MathHelper.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * MathHelper.sin(this.angle)));
        }
    }


    class PickAttackGoal extends Goal {
        private int nextSweepTick;

        private PickAttackGoal() {
        }

        public boolean canUse() {
            LivingEntity livingentity = JetEntity.this.getTarget();
            return livingentity != null ? JetEntity.this.canAttack(JetEntity.this.getTarget(), EntityPredicate.DEFAULT) : false;
        }

        public void start() {
            this.nextSweepTick = 10;
            JetEntity.this.attackPhase = JetEntity.AttackPhase.CIRCLE;
            this.setAnchorAboveTarget();
        }

        public void stop() {
            JetEntity.this.anchorPoint = JetEntity.this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, JetEntity.this.anchorPoint).above(10 + JetEntity.this.random.nextInt(20));
        }

        public void tick() {
            if (JetEntity.this.attackPhase == JetEntity.AttackPhase.CIRCLE) {
                --this.nextSweepTick;
                if (this.nextSweepTick <= 0) {
                    JetEntity.this.attackPhase = JetEntity.AttackPhase.SWOOP;
                    this.setAnchorAboveTarget();
                    this.nextSweepTick = (8 + JetEntity.this.random.nextInt(4)) * 20;
                    JetEntity.this.playSound(SoundEvents.PHANTOM_SWOOP, 10.0F, 0.95F + JetEntity.this.random.nextFloat() * 0.1F);
                }
            }

        }

        private void setAnchorAboveTarget() {
            JetEntity.this.anchorPoint = JetEntity.this.getTarget().blockPosition().above(20 + JetEntity.this.random.nextInt(20));
            if (JetEntity.this.anchorPoint.getY() < JetEntity.this.level.getSeaLevel()) {
                JetEntity.this.anchorPoint = new BlockPos(JetEntity.this.anchorPoint.getX(), JetEntity.this.level.getSeaLevel() + 1, JetEntity.this.anchorPoint.getZ());
            }

        }
    }

    class SweepAttackGoal extends JetEntity.MoveGoal {
        private SweepAttackGoal() {
        }

        public boolean canUse() {
            return JetEntity.this.getTarget() != null && JetEntity.this.attackPhase == JetEntity.AttackPhase.SWOOP;
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = JetEntity.this.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (!(livingentity instanceof PlayerEntity) || !((PlayerEntity)livingentity).isSpectator() && !((PlayerEntity)livingentity).isCreative()) {
                if (!this.canUse()) {
                    return false;
                } else {
                    if (JetEntity.this.tickCount % 20 == 0) {
                        List<CatEntity> list = JetEntity.this.level.getEntitiesOfClass(CatEntity.class, JetEntity.this.getBoundingBox().inflate(16.0D), EntityPredicates.ENTITY_STILL_ALIVE);
                        if (!list.isEmpty()) {
                            for(CatEntity catentity : list) {
                                catentity.hiss();
                            }

                            return false;
                        }
                    }

                    return true;
                }
            } else {
                return false;
            }
        }

        public void start() {
        }

        public void stop() {
            JetEntity.this.setTarget((LivingEntity)null);
            JetEntity.this.attackPhase = JetEntity.AttackPhase.CIRCLE;
        }

        public void tick() {
            LivingEntity livingentity = JetEntity.this.getTarget();
            JetEntity.this.moveTargetPoint = new Vector3d(livingentity.getX(), livingentity.getY(0.5D), livingentity.getZ());
            if (JetEntity.this.getBoundingBox().inflate((double)0.2F).intersects(livingentity.getBoundingBox())) {
                JetEntity.this.doHurtTarget(livingentity);
                JetEntity.this.attackPhase = JetEntity.AttackPhase.CIRCLE;
                if (!JetEntity.this.isSilent()) {
                    JetEntity.this.level.levelEvent(1039, JetEntity.this.blockPosition(), 0);
                }
            } else if (JetEntity.this.horizontalCollision || JetEntity.this.hurtTime > 0) {
                JetEntity.this.attackPhase = JetEntity.AttackPhase.CIRCLE;
            }

        }
    }

    public void die(DamageSource source)
    {
        super.die(source);

        if (!(source.getEntity() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity) source.getEntity();
        if (!player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get().getItem().asItem()))
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

}

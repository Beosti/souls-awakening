package com.yuanno.soulsawakening.entity;

import com.google.common.base.Predicates;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.entities.hollow.IBleach;
import com.yuanno.soulsawakening.entities.hollow.ThornsEntity;
import com.yuanno.soulsawakening.entity.goal.ImprovedMeleeAttackShinigamiGoal;
import com.yuanno.soulsawakening.entities.hollow.HollowEntity;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModQuests;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.init.world.ModDimensions;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Predicate;

public class ShinigamiEntity extends CreatureEntity implements IBleach {

    public String[] options = {"shinigami_pink", "shinigami_normal", "shinigami_yellow"};
    public String constantSkin = "";
    public ShinigamiEntity(EntityType type, World world)
    {
        super(type, world);
    }

    public String getSkin()
    {
        if (!constantSkin.isEmpty())
            return constantSkin;
        Random random = new Random();
        int randomIndex = random.nextInt(options.length);
        this.constantSkin = options[randomIndex];
        return this.constantSkin;
    }

    @Override
    public String getRace() {
        return ModValues.SHINIGAMI;
    }

    @Override
    public String getRank() {
        return ModValues.NON_OFFICER;
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        Predicate<Entity> factionScope = getEnemyFactions(this);

        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, HollowEntity.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, ThornsEntity.class, false));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, true, factionScope));
        this.targetSelector.addGoal(7, new LeapAtTargetGoal(this, 0.4f));

        this.goalSelector.addGoal(7, new ImprovedMeleeAttackShinigamiGoal(this, 1, true));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 4));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
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

            IEntityStats entityStats = EntityStatsCapability.get((LivingEntity) target);
            World world = ((LivingEntity) target).level;
            MinecraftServer minecraftServer = world.getServer();
            ServerWorld soulSociety = minecraftServer.getLevel(ModDimensions.SOUL_SOCIETY);
            IQuestData questData = QuestDataCapability.get((LivingEntity) target);
            if (questData.hasInProgressQuest(ModQuests.KILLHOLLOW) && entityStats.getRace().equals(ModValues.SPIRIT))
                return false;
            if (entityStats.getRace().equals(ModValues.HOLLOW))
                return true;
            else if (entityStats.getRace().equals(ModValues.SPIRIT) && world.dimension() != ModDimensions.SOUL_SOCIETY)
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
        Random randomTest = new Random();
        this.setItemSlot(EquipmentSlotType.MAINHAND, swordStack);
        int randomIndex = randomTest.nextInt(options.length);
        this.constantSkin = options[randomIndex];
        IEntityStats entityStats = EntityStatsCapability.get(this);
        entityStats.setRace(ModValues.SHINIGAMI);
        entityStats.setReiatsuPoints(10);
        return spawnData;
    }
}

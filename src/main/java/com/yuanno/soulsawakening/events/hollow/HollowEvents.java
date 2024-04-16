package com.yuanno.soulsawakening.events.hollow;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.CeroAbility;
import com.yuanno.soulsawakening.abilities.hollow.GargantaAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.entities.hollow.IBleach;
import com.yuanno.soulsawakening.events.UpdateStatEvent;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.w3c.dom.Attr;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HollowEvents {
    public static UUID healthUUIDAttribute = UUID.fromString("09c19aa8-fc0f-11ee-a951-0242ac120002");

    public static UUID hierroUUIDAttribute = UUID.fromString("90afe890-fc13-11ee-a951-0242ac120002");

    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);
    public static final ParticleEffect PARTICLES_WAVE_ADJUCHA = new WaveParticleEffect(1.6);
    public static final ParticleEffect PARTICLES_WAVE_VASTO = new WaveParticleEffect(1.8);


    /**
     * Updated all the hollow stat that needs to be updated
     * @param event
     */
    @SubscribeEvent
    public static void onUpdateHollowStat(UpdateStatEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (!entityStats.getRace().equals(ModValues.HOLLOW))
            return;
        handleConstitution(player, entityStats);
        handleHierro(player, entityStats);
    }

    static void handleHierro(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance damageReductionAttribute = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
        damageReductionAttribute.setBaseValue(entityStats.getHollowStats().getHierro() * 0.05);
    }

    // handles the constitution stat
    static void handleConstitution(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance maxHpAttribute = player.getAttribute(Attributes.MAX_HEALTH);
        maxHpAttribute.setBaseValue(20 + (float) (entityStats.getHollowStats().getConstitution()));
        ((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));
    }

    /**
     * When a hollow kills something it gains points.
     * That's handled here
     * @param event
     */
    @SubscribeEvent
    public static void onHollowKillEvent(LivingDeathEvent event)
    {
        if (event.getEntityLiving() != null && event.getSource().getDirectEntity() != null && !event.getEntityLiving().level.isClientSide && event.getSource().getDirectEntity() instanceof LivingEntity)
        {
            LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
            if (!EntityStatsCapability.get(attacker).getRace().equals(ModValues.HOLLOW))
                return;
            LivingEntity livingEntity = event.getEntityLiving();
            if (!(livingEntity instanceof IBleach))
                return;
            if (((IBleach) livingEntity).getRace().equals(ModValues.HOLLOW))
                handleHollowDeath(livingEntity, attacker);
            if (((IBleach) livingEntity).getRace().equals(ModValues.SHINIGAMI))
                handleShinigamiDeath(livingEntity, attacker);
            if (((IBleach) livingEntity).getRace().equals(ModValues.SPIRIT))
                handleSpiritDeath(livingEntity, attacker);
        }
    }
    static void handleHollowDeath(LivingEntity livingEntity, LivingEntity attacker)
    {
        String rank = ((IBleach) livingEntity).getRank();
        if (rank.equals(ModValues.BASE))
            EntityStatsCapability.get(attacker).getHollowStats().alterMutationPoints(1);
        if (attacker instanceof PlayerEntity)
            PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), EntityStatsCapability.get(attacker)), ((PlayerEntity)attacker));
    }
    static void handleShinigamiDeath(LivingEntity livingEntity, LivingEntity attacker)
    {
        String rank = ((IBleach) livingEntity).getRank();
        if (rank.equals(ModValues.NON_OFFICER))
            EntityStatsCapability.get(attacker).getHollowStats().alterHollowPoints(1);
        if (attacker instanceof PlayerEntity)
            PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), EntityStatsCapability.get(attacker)), ((PlayerEntity)attacker));
    }
    static void handleSpiritDeath(LivingEntity livingEntity, LivingEntity attacker)
    {
        EntityStatsCapability.get(attacker).getHollowStats().alterHollowPoints(1);
        if (attacker instanceof PlayerEntity)
            PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), EntityStatsCapability.get(attacker)), ((PlayerEntity)attacker));
    }

    /**
     * Handles the evolution of hollows, little effect and added attributes
     * @param event
     */
    @SubscribeEvent
    public static void hollowEvolutionEvent(HollowEvolutionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;

        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        AttributeModifier attributeModifierGillian = new AttributeModifier(UUID.fromString("4658d71c-a663-11ee-a506-0242ac120002"), "Gillian attack bonus", 5, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierAdjucha = new AttributeModifier(UUID.fromString("56925e5a-a663-11ee-a506-0242ac120002"), "Adjucha Speed Bonus", 0.10, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierVastoLordeAttackSpeed = new AttributeModifier(UUID.fromString("56925e5a-a663-11ee-a506-0242ac120002"), "Vasto Lorde Attack Speed Bonus", 1, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierVastoLordeAttack = new AttributeModifier(UUID.fromString("56925e5a-a663-11ee-a506-0242ac120002"), "Vasto Lorde Attack Bonus", 5, AttributeModifier.Operation.ADDITION);
        System.out.println(entityStats.getRank());
        switch (entityStats.getRank())
        {
            case (ModValues.BASE):
                System.out.println("CALLED");
                PARTICLES_WAVE.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.HOLLOW.get());
                entityStats.setRank(ModValues.GILLIAN);
                abilityData.addUnlockedAbility(CeroAbility.INSTANCE);
                player.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(attributeModifierGillian);
                ModAdvancements.GILLIAN.trigger((ServerPlayerEntity) player);
                break;
            case (ModValues.GILLIAN):
                PARTICLES_WAVE_ADJUCHA.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.HOLLOW.get());
                entityStats.setRank(ModValues.ADJUCHA);
                abilityData.addUnlockedAbility(GargantaAbility.INSTANCE);
                player.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(attributeModifierAdjucha);
                ModAdvancements.ADJUCHA.trigger((ServerPlayerEntity) player);
                break;
            case (ModValues.ADJUCHA):
                        PARTICLES_WAVE_VASTO.spawn(player.level, player.getX(), player.getY(), player.getZ(), 0, 0, 0, ModParticleTypes.HOLLOW.get());
                        entityStats.setRank(ModValues.VASTO_LORDE);
                        player.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(attributeModifierVastoLordeAttack);
                        player.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(attributeModifierVastoLordeAttackSpeed);
                        ModAdvancements.VASTO_LORDE.trigger((ServerPlayerEntity) player);
                        break;
                        /*
                        case (ModValues.VASTO_LORDE):
                            entityStats.setRank(ModValues.ARRANCAR);
                            break;

                         */
            }
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }
}
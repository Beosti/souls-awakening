package com.yuanno.soulsawakening.events.hollow;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.CeroAbility;
import com.yuanno.soulsawakening.abilities.hollow.GargantaAbility;
import com.yuanno.soulsawakening.client.renderers.overlay.HollowModelRenderer;
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
import com.yuanno.soulsawakening.models.hollow.ApeModel;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.w3c.dom.Attr;

import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HollowEvents {

    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);
    public static final ParticleEffect PARTICLES_WAVE_ADJUCHA = new WaveParticleEffect(1.6);
    public static final ParticleEffect PARTICLES_WAVE_VASTO = new WaveParticleEffect(1.8);


    @SubscribeEvent
    public static void onHollowModelRendering(RenderPlayerEvent.Pre event)
    {
        PlayerEntity player = event.getPlayer();
        IEntityStats entityStats = EntityStatsCapability.get(player);
        if (entityStats.getRace().equals(ModValues.HOLLOW) && entityStats.getRank().equals(ModValues.BASE)) {
            //HollowModelRenderer.render(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEntityLiving(), 0, 0, 0, 0, 0, 0);
            //player.
            //event.setCanceled(true);
        }
    }
    /**
     * Updated all the hollow stat that needs to be updated if used in the player overview screen
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

    // handles the hierro stat; update the damage resistance based on hierro stat
    static void handleHierro(PlayerEntity player, IEntityStats entityStats)
    {
        ModifiableAttributeInstance damageReductionAttribute = player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get());
        damageReductionAttribute.setBaseValue(entityStats.getHollowStats().getHierro() * 0.05);
    }

    // handles the constitution stat; update the max health stat based on constitution stat
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
            if (event.getSource().getDirectEntity().level.getBiome(event.getSource().getDirectEntity().blockPosition()).getRegistryName().toString().equals("minecraft:the_void"))
                event.setCanceled(true);
            if (!EntityStatsCapability.get(attacker).getRace().equals(ModValues.HOLLOW))
                return;
            if (!EntityStatsCapability.get(attacker).hasHollowStats())
                return;
            LivingEntity livingEntity = event.getEntityLiving();
            if (!(livingEntity instanceof IBleach))
                return;
            if (!(livingEntity instanceof PlayerEntity))
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
        if (rank.equals(ModValues.BASE)) {
            EntityStatsCapability.get(attacker).getHollowStats().alterHollowPoints(1);
            if (attacker instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) attacker;
                try
                {
                    ((ServerPlayerEntity) player).connection.send(new STitlePacket(3, 10, 3));
                    ITextComponent titleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), new TranslationTextComponent("hollow.hollow_point.text", "Gained a hollow point"), player, 0);
                    ((ServerPlayerEntity) player).connection.send(new STitlePacket(STitlePacket.Type.ACTIONBAR, titleComponent));
                }
                catch (Exception e)
                {
                    e.printStackTrace();;
                }
            }
        }
        if (attacker instanceof PlayerEntity)
            PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), EntityStatsCapability.get(attacker)), ((PlayerEntity)attacker));
    }
    static void handleShinigamiDeath(LivingEntity livingEntity, LivingEntity attacker)
    {
        String rank = ((IBleach) livingEntity).getRank();
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int chancePercentage = 0;
        if (rank.equals(ModValues.NON_OFFICER))
            chancePercentage = 25;
        IEntityStats entityStats = EntityStatsCapability.get(attacker);
        PlayerEntity player = (PlayerEntity) attacker;
        if (attacker instanceof PlayerEntity) {
            {
                if (chancePercentage > randomNumber)
                {
                    entityStats.getHollowStats().alterMutationPoints(1);
                    try
                    {
                        ((ServerPlayerEntity) player).connection.send(new STitlePacket(3, 10, 3));
                        ITextComponent titleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), new TranslationTextComponent("hollow.mutation_point.text", "§7Gained a mutation point"), player, 0);
                        ((ServerPlayerEntity) player).connection.send(new STitlePacket(STitlePacket.Type.ACTIONBAR, titleComponent));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), EntityStatsCapability.get(attacker)), ((PlayerEntity) attacker));
            }
        }
    }
    static void handleSpiritDeath(LivingEntity livingEntity, LivingEntity attacker)
    {
        IEntityStats entityStats = EntityStatsCapability.get(attacker);
        PlayerEntity player = (PlayerEntity) attacker;
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int chancePercentage = 25;
        if (attacker instanceof PlayerEntity) {
            {
                if (chancePercentage > randomNumber)
                {
                    entityStats.getHollowStats().alterMutationPoints(1);
                    try
                    {
                        ((ServerPlayerEntity) player).connection.send(new STitlePacket(3, 10, 3));
                        ITextComponent titleComponent = TextComponentUtils.updateForEntity(player.createCommandSourceStack(), new TranslationTextComponent("hollow.mutation_point.text", "§7Gained a mutation point"), player, 0);
                        ((ServerPlayerEntity) player).connection.send(new STitlePacket(STitlePacket.Type.ACTIONBAR, titleComponent));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                PacketHandler.sendTo(new SSyncEntityStatsPacket(attacker.getId(), EntityStatsCapability.get(attacker)), ((PlayerEntity) attacker));
            }
        }
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
        entityStats.getHollowStats().setHollowPoints(0);
        AttributeModifier attributeModifierGillian = new AttributeModifier(UUID.fromString("4658d71c-a663-11ee-a506-0242ac120002"), "Gillian attack bonus", 5, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierAdjucha = new AttributeModifier(UUID.fromString("56925e5a-a663-11ee-a506-0242ac120002"), "Adjucha Speed Bonus", 0.10, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierVastoLordeAttackSpeed = new AttributeModifier(UUID.fromString("56925e5a-a663-11ee-a506-0242ac120002"), "Vasto Lorde Attack Speed Bonus", 1, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierVastoLordeAttack = new AttributeModifier(UUID.fromString("56925e5a-a663-11ee-a506-0242ac120002"), "Vasto Lorde Attack Bonus", 5, AttributeModifier.Operation.ADDITION);
        switch (entityStats.getRank())
        {
            case (ModValues.BASE):
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

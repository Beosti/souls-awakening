package com.yuanno.soulsawakening.events.hollow;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.CeroAbility;
import com.yuanno.soulsawakening.abilities.hollow.GargantaAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModAdvancements;
import com.yuanno.soulsawakening.init.ModParticleTypes;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.particles.ParticleEffect;
import com.yuanno.soulsawakening.particles.api.WaveParticleEffect;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HollowSpecificEvents {
    public static final UUID GILLIAN_ATTACK_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");
    public static final UUID ADJUCHA_SPEED_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");
    public static final UUID VASTO_LORDE_ATTACK_SPEED_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");
    public static final UUID VASTO_LORDE_ATTACK_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");

    public static final ParticleEffect PARTICLES_WAVE = new WaveParticleEffect(1.4);
    public static final ParticleEffect PARTICLES_WAVE_ADJUCHA = new WaveParticleEffect(1.6);
    public static final ParticleEffect PARTICLES_WAVE_VASTO = new WaveParticleEffect(1.8);


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

        switch (entityStats.getRank()) {
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
            entityStats.setHollowPoints(0);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }
}

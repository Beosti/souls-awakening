package com.yuanno.soulsawakening.api.ability;

import com.yuanno.soulsawakening.init.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SUpdateHealthPacket;

import java.util.UUID;

public class AttributeHelper
{

	public static final UUID PASSIVE_TOUGHNESS_UUID = UUID.fromString("18837309-bfc8-483d-8c40-44ab292e6776");

	/**
	 * UUID obtained from this Item.ATTACK_DAMAGE_MODIFIER which is protected so I
	 * cannot simply reference it here
	 */
	public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

	/**
	 * UUID obtained from this LivingEntity.SPEED_MODIFIER_SPRINTING_UUID which is
	 * private so I cannot simply reference it here
	 */
	public static final UUID SPEED_MODIFIER_SPRINTING_UUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");

	/*
	public static void updateHPAttribute(PlayerEntity player)
	{
		if (player != null && !player.level.isClientSide && CommonConfig.INSTANCE.isExtraHeartsEnabled())
		{
			ModifiableAttributeInstance maxHpAttribute = player.getAttribute(Attributes.MAX_HEALTH);
			IEntityStats props = EntityStatsCapability.get(player);
			int freq = CommonConfig.INSTANCE.getHealthGainFrequency();
			
			if (props.getDoriki() / freq > 20)
				maxHpAttribute.setBaseValue(props.getDoriki() / freq);
			else
				maxHpAttribute.setBaseValue(20);
			
			if(player.getHealth() > maxHpAttribute.getValue())
				player.setHealth((float) maxHpAttribute.getValue());
			
			((ServerPlayerEntity) player).connection.send(new SUpdateHealthPacket(player.getHealth(), player.getFoodData().getFoodLevel(), player.getFoodData().getSaturationLevel()));
		}
	}
	
	public static void updateToughnessAttribute(PlayerEntity player)
	{
		if (player != null && !player.level.isClientSide) {
			ModifiableAttributeInstance toughnessAttribute = player.getAttribute(ModAttributes.TOUGHNESS.get());
			if(toughnessAttribute == null) {
				return;
			}
			IEntityStats props = EntityStatsCapability.get(player);
			
			AttributeModifier mod = new AttributeModifier(PASSIVE_TOUGHNESS_UUID, "Passive Toughness Bonus", 0.08D * (props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit()) * 100, AttributeModifier.Operation.ADDITION);
			
			player.getAttribute(ModAttributes.TOUGHNESS.get()).removeModifier(PASSIVE_TOUGHNESS_UUID);
			player.getAttribute(ModAttributes.TOUGHNESS.get()).addTransientModifier(mod);
		}
	}

	 */

	public static double getAttackRangeDistance(final LivingEntity entity, final double baseReachDistance)
	{
		final ModifiableAttributeInstance reachDistance = entity.getAttribute(ModAttributes.ATTACK_RANGE.get());
		return (reachDistance != null) ? (baseReachDistance + reachDistance.getValue()) : baseReachDistance;
	}

	public static double getSquaredAttackRangeDistance(final LivingEntity entity, final double sqBaseReachDistance)
	{
		final double reachDistance = getAttackRangeDistance(entity, Math.sqrt(sqBaseReachDistance));
		return reachDistance * reachDistance;
	}
}

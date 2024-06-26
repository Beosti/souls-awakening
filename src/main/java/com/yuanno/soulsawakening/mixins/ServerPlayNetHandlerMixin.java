package com.yuanno.soulsawakening.mixins;

import com.yuanno.soulsawakening.api.ability.Ability;
import com.yuanno.soulsawakening.api.ability.AttributeHelper;
import com.yuanno.soulsawakening.api.ability.interfaces.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CClientStatusPacket;
import net.minecraft.network.play.client.CUseEntityPacket;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetHandler.class)
public abstract class ServerPlayNetHandlerMixin implements IServerPlayNetHandler
{
	@Shadow
	public ServerPlayerEntity player;

	@Shadow
	@Final
	public MinecraftServer server;
	
	@ModifyConstant(
		method = "handleInteract(Lnet/minecraft/network/play/client/CUseEntityPacket;)V", 
		constant = @Constant(doubleValue = 36.0)
	)
	public double getActualAttackRange(double attackRange, CUseEntityPacket packet)
	{
		if (packet.getAction() == CUseEntityPacket.Action.ATTACK)
			return AttributeHelper.getSquaredAttackRangeDistance(this.player, attackRange);

		return AttributeHelper.getSquaredAttackRangeDistance(this.player, attackRange);
	}
}

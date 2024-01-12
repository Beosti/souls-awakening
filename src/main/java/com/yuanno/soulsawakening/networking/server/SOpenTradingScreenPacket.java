package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.screens.ChallengesScreen;
import com.yuanno.soulsawakening.screens.TradingScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenTradingScreenPacket
{
	public SOpenTradingScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenTradingScreenPacket decode(PacketBuffer buffer)
	{
		SOpenTradingScreenPacket msg = new SOpenTradingScreenPacket();
		return msg;
	}

	public static void handle(SOpenTradingScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenTradingScreenPacket message)
		{
			PlayerEntity player = Minecraft.getInstance().player;;
			TradingScreen.open(player);
		}
	}
}

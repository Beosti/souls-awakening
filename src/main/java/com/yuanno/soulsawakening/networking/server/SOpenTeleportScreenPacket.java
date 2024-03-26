package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.screens.AbilityListScreen;
import com.yuanno.soulsawakening.screens.TeleportsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenTeleportScreenPacket
{
	public SOpenTeleportScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenTeleportScreenPacket decode(PacketBuffer buffer)
	{
		SOpenTeleportScreenPacket msg = new SOpenTeleportScreenPacket();
		return msg;
	}

	public static void handle(SOpenTeleportScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenTeleportScreenPacket message)
		{
			TeleportsScreen.open();
		}
	}
}

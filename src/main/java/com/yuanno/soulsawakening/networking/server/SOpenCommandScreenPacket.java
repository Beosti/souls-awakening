package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.client.screen.CommandZanpakutoScreen;
import com.yuanno.soulsawakening.screens.AbilityListScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenCommandScreenPacket
{
	public SOpenCommandScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenCommandScreenPacket decode(PacketBuffer buffer)
	{
		SOpenCommandScreenPacket msg = new SOpenCommandScreenPacket();
		return msg;
	}

	public static void handle(SOpenCommandScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenCommandScreenPacket message)
		{
			PlayerEntity player = Minecraft.getInstance().player;;
			CommandZanpakutoScreen.open();
		}
	}
}

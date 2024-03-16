package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.client.screen.ChatPromptScreen;
import com.yuanno.soulsawakening.screens.ChallengesScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenChatPromptScreenPacket
{
	public SOpenChatPromptScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenChatPromptScreenPacket decode(PacketBuffer buffer)
	{
		SOpenChatPromptScreenPacket msg = new SOpenChatPromptScreenPacket();
		return msg;
	}

	public static void handle(SOpenChatPromptScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenChatPromptScreenPacket message)
		{
			ChatPromptScreen.open();
		}
	}
}

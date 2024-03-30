package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.client.screen.ChatPromptScreen;
import com.yuanno.soulsawakening.screens.ChallengesScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenChatPromptScreenPacket
{
	private int entityID;
	public SOpenChatPromptScreenPacket() {}

	public SOpenChatPromptScreenPacket(int entityID)
	{
		this.entityID = entityID;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityID);
	}

	public static SOpenChatPromptScreenPacket decode(PacketBuffer buffer)
	{
		SOpenChatPromptScreenPacket msg = new SOpenChatPromptScreenPacket();
		msg.entityID = buffer.readInt();
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
			Entity entity = Minecraft.getInstance().level.getEntity(message.entityID);
			ChatPromptScreen.open(entity);
		}
	}
}

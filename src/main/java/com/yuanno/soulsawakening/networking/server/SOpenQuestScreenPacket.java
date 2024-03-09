package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.screens.QuestListScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenQuestScreenPacket
{
	public SOpenQuestScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenQuestScreenPacket decode(PacketBuffer buffer)
	{
		SOpenQuestScreenPacket msg = new SOpenQuestScreenPacket();
		return msg;
	}

	public static void handle(SOpenQuestScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenQuestScreenPacket message)
		{
			QuestListScreen.open();
		}
	}
}

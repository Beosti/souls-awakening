package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.screens.ChallengesScreen;
import com.yuanno.soulsawakening.screens.PlayerOverviewScreen;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenChallengeScreenPacket
{
	public SOpenChallengeScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static SOpenChallengeScreenPacket decode(PacketBuffer buffer)
	{
		SOpenChallengeScreenPacket msg = new SOpenChallengeScreenPacket();
		return msg;
	}

	public static void handle(SOpenChallengeScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
			ctx.get().enqueueWork(() -> ClientHandler.handle(message));
		ctx.get().setPacketHandled(true);
	}

	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SOpenChallengeScreenPacket message)
		{
			ChallengesScreen.open();
		}
	}
}

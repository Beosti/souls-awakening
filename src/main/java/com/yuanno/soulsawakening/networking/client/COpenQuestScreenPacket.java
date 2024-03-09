package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SOpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.server.SOpenQuestScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class COpenQuestScreenPacket
{
	public COpenQuestScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static COpenQuestScreenPacket decode(PacketBuffer buffer)
	{
		COpenQuestScreenPacket msg = new COpenQuestScreenPacket();

		return msg;
	}
	
	public static void handle(COpenQuestScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				PacketHandler.sendTo(new SOpenQuestScreenPacket(), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}

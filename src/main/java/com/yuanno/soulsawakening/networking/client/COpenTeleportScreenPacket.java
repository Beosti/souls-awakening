package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SOpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.server.SOpenTeleportScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class COpenTeleportScreenPacket
{
	public COpenTeleportScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static COpenTeleportScreenPacket decode(PacketBuffer buffer)
	{
		COpenTeleportScreenPacket msg = new COpenTeleportScreenPacket();

		return msg;
	}
	
	public static void handle(COpenTeleportScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				PacketHandler.sendTo(new SOpenTeleportScreenPacket(), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}

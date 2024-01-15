package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SOpenAbilityScreenPacket;
import com.yuanno.soulsawakening.networking.server.SOpenTradingScreenPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class COpenAbilityListScreenPacket
{
	public COpenAbilityListScreenPacket() {}

	public void encode(PacketBuffer buffer)
	{
	}

	public static COpenAbilityListScreenPacket decode(PacketBuffer buffer)
	{
		COpenAbilityListScreenPacket msg = new COpenAbilityListScreenPacket();

		return msg;
	}
	
	public static void handle(COpenAbilityListScreenPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();

				PacketHandler.sendTo(new SOpenAbilityScreenPacket(), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}
}

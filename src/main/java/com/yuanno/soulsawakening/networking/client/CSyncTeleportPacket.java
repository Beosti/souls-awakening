package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncQuestDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncTeleportPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


public class CSyncTeleportPacket
{
	private INBT data;

	public CSyncTeleportPacket() {}

	public CSyncTeleportPacket(ITeleports props)
	{
		this.data = new CompoundNBT();
		this.data = TeleportCapability.INSTANCE.getStorage().writeNBT(TeleportCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeNbt((CompoundNBT) this.data);
	}

	public static CSyncTeleportPacket decode(PacketBuffer buffer)
	{
		CSyncTeleportPacket msg = new CSyncTeleportPacket();
		msg.data = buffer.readNbt();
		return msg;
	}

	public static void handle(CSyncTeleportPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				ITeleports props = TeleportCapability.get(player);

				TeleportCapability.INSTANCE.getStorage().readNBT(TeleportCapability.INSTANCE, props, null, message.data);
				PacketHandler.sendTo(new SSyncTeleportPacket(player.getId(), props), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}

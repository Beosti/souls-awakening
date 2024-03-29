package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.quest.IQuestData;
import com.yuanno.soulsawakening.data.quest.QuestDataCapability;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncQuestDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;


public class CSyncQuestDataPacket
{
	private INBT data;

	public CSyncQuestDataPacket() {}

	public CSyncQuestDataPacket(IQuestData props)
	{
		this.data = new CompoundNBT();
		this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeNbt((CompoundNBT) this.data);
	}

	public static CSyncQuestDataPacket decode(PacketBuffer buffer)
	{
		CSyncQuestDataPacket msg = new CSyncQuestDataPacket();
		msg.data = buffer.readNbt();
		return msg;
	}

	public static void handle(CSyncQuestDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
		{
			ctx.get().enqueueWork(() ->
			{
				PlayerEntity player = ctx.get().getSender();
				IQuestData props = QuestDataCapability.get(player);

				QuestDataCapability.INSTANCE.getStorage().readNBT(QuestDataCapability.INSTANCE, props, null, message.data);
				PacketHandler.sendTo(new SSyncQuestDataPacket(player.getId(), props), player);
			});
		}
		ctx.get().setPacketHandled(true);
	}

}

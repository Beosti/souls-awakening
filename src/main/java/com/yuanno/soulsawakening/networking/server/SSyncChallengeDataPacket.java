package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.data.challenges.ChallengesDataCapability;
import com.yuanno.soulsawakening.data.challenges.IChallengesData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncChallengeDataPacket
{
	private int entityId;
	private INBT data;

	public SSyncChallengeDataPacket()
	{
	}

	public SSyncChallengeDataPacket(int entityId, IChallengesData props)
	{
		this.data = new CompoundNBT();
		this.data = ChallengesDataCapability.INSTANCE.getStorage().writeNBT(ChallengesDataCapability.INSTANCE, props, null);
		this.entityId = entityId;
	}

	public void encode(PacketBuffer buffer)
	{
		buffer.writeInt(this.entityId);
		buffer.writeNbt((CompoundNBT) this.data);
	}

	public static SSyncChallengeDataPacket decode(PacketBuffer buffer)
	{
		SSyncChallengeDataPacket msg = new SSyncChallengeDataPacket();
		msg.entityId = buffer.readInt();
		msg.data = buffer.readNbt();
		return msg;
	}

	public static void handle(SSyncChallengeDataPacket message, final Supplier<NetworkEvent.Context> ctx)
	{
		if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_CLIENT)
		{
			ctx.get().enqueueWork(() ->
			{
				ClientHandler.handle(message);
			});
		}
		ctx.get().setPacketHandled(true);
	}
	
	public static class ClientHandler
	{
		@OnlyIn(Dist.CLIENT)
		public static void handle(SSyncChallengeDataPacket message)
		{
			Entity target = Minecraft.getInstance().level.getEntity(message.entityId);
			if (target == null || !(target instanceof PlayerEntity))
				return;

			IChallengesData props = ChallengesDataCapability.get((PlayerEntity) target);
			ChallengesDataCapability.INSTANCE.getStorage().readNBT(ChallengesDataCapability.INSTANCE, props, null, message.data);		
		}
	}

}

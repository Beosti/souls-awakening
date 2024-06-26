package com.yuanno.soulsawakening.networking.server;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.teleports.ITeleports;
import com.yuanno.soulsawakening.data.teleports.TeleportCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncTeleportPacket {

    private int entityId;
    private INBT data;

    public SSyncTeleportPacket()
    {
    }

    public SSyncTeleportPacket(int entityId, ITeleports props)
    {
        this.data = new CompoundNBT();
        this.data = TeleportCapability.INSTANCE.getStorage().writeNBT(TeleportCapability.INSTANCE, props, null);
        this.entityId = entityId;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.entityId);
        buffer.writeNbt((CompoundNBT) this.data);
    }

    public static SSyncTeleportPacket decode(PacketBuffer buffer)
    {
        SSyncTeleportPacket msg = new SSyncTeleportPacket();
        msg.entityId = buffer.readInt();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(SSyncTeleportPacket message, final Supplier<NetworkEvent.Context> ctx)
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
        public static void handle(SSyncTeleportPacket message)
        {
            Entity target = Minecraft.getInstance().level.getEntity(message.entityId);
            if (target == null || !(target instanceof LivingEntity))
                return;

            ITeleports props = TeleportCapability.get((LivingEntity) target);
            TeleportCapability.INSTANCE.getStorage().readNBT(TeleportCapability.INSTANCE, props, null, message.data);

        }
    }
}

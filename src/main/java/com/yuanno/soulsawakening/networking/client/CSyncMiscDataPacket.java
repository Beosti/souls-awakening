package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.data.misc.IMiscData;
import com.yuanno.soulsawakening.data.misc.MiscDataCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncMiscDataPacket {

    private INBT data;

    public CSyncMiscDataPacket() {}

    public CSyncMiscDataPacket(IMiscData props)
    {
        this.data = new CompoundNBT();
        this.data = MiscDataCapability.INSTANCE.getStorage().writeNBT(MiscDataCapability.INSTANCE, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt((CompoundNBT) data);
    }

    public static CSyncMiscDataPacket decode(PacketBuffer buffer)
    {
        CSyncMiscDataPacket msg = new CSyncMiscDataPacket();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(CSyncMiscDataPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IMiscData props = MiscDataCapability.get(player);

                MiscDataCapability.INSTANCE.getStorage().readNBT(MiscDataCapability.INSTANCE, props, null, message.data);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}

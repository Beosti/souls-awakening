package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.stats.HakudaGainEvent;
import com.yuanno.soulsawakening.events.stats.HohoGainEvent;
import com.yuanno.soulsawakening.events.stats.HollowGainEvent;
import com.yuanno.soulsawakening.events.stats.ZanjutsuGainEvent;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncentityStatsStatsPacket {

    private INBT data;

    public CSyncentityStatsStatsPacket() {}

    public CSyncentityStatsStatsPacket(IEntityStats props)
    {
        this.data = new CompoundNBT();
        this.data = EntityStatsCapability.INSTANCE.getStorage().writeNBT(EntityStatsCapability.INSTANCE, props, null);
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt((CompoundNBT) data);
    }

    public static CSyncentityStatsStatsPacket decode(PacketBuffer buffer)
    {
        CSyncentityStatsStatsPacket msg = new CSyncentityStatsStatsPacket();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(CSyncentityStatsStatsPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IEntityStats props = EntityStatsCapability.get(player);
                EntityStatsCapability.INSTANCE.getStorage().readNBT(EntityStatsCapability.INSTANCE, props, null, message.data);
                ZanjutsuGainEvent zanjutsuGainEvent = new ZanjutsuGainEvent(player);
                if (props.getRace().equals(ModValues.FULLBRINGER) || props.getRace().equals(ModValues.SHINIGAMI))
                {
                    if (MinecraftForge.EVENT_BUS.post(zanjutsuGainEvent))
                        return;
                    HohoGainEvent hohoGainEvent = new HohoGainEvent(player);
                    if (MinecraftForge.EVENT_BUS.post(hohoGainEvent))
                        return;
                    HakudaGainEvent hakudaGainEvent = new HakudaGainEvent(player);
                    if (MinecraftForge.EVENT_BUS.post(hakudaGainEvent))
                        return;
                }
                else if (props.getRace().equals(ModValues.HOLLOW))
                {
                    HollowGainEvent hollowGainEvent = new HollowGainEvent(player);
                    if (MinecraftForge.EVENT_BUS.post(hollowGainEvent))
                        return;
                }
            });
        }
        ctx.get().setPacketHandled(true);
    }

}

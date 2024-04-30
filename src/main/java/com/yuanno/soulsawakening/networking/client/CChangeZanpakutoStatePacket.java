package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.events.util.ZanpakutoChangeEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CChangeZanpakutoStatePacket {

    private int stateNumber = 0;
    public CChangeZanpakutoStatePacket()
    {

    }



    public void encode(PacketBuffer packetBuffer)
    {
        packetBuffer.writeInt(this.stateNumber);
    }

    public static CChangeZanpakutoStatePacket decode(PacketBuffer packetBuffer)
    {
        CChangeZanpakutoStatePacket msg = new CChangeZanpakutoStatePacket();
        msg.stateNumber = packetBuffer.readInt();
        return msg;
    }

    public static void handle(CChangeZanpakutoStatePacket message, final Supplier<NetworkEvent.Context> contextSupplier)
    {
        if (contextSupplier.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            contextSupplier.get().enqueueWork(() ->
            {
                PlayerEntity player = contextSupplier.get().getSender();
                ZanpakutoChangeEvent zanpakutoChangeEvent = new ZanpakutoChangeEvent(player);
                MinecraftForge.EVENT_BUS.post(zanpakutoChangeEvent);
            });
        }
        contextSupplier.get().setPacketHandled(true);
    }
}

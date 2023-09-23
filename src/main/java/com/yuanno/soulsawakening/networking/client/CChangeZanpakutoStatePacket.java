package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CChangeZanpakutoStatePacket {

    private int stateNumber = 0;
    public CChangeZanpakutoStatePacket()
    {

    }

    public CChangeZanpakutoStatePacket(ZanpakutoItem.STATE state)
    {
        switch (state)
        {
            case SEALED:
              this.stateNumber = 0;
              break;
            case SHIKAI:
                this.stateNumber = 1;
                break;
            case BANKAI:
                this.stateNumber = 2;
                break;
        }
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
                if (!player.getMainHandItem().isEmpty() && player.getMainHandItem().getItem().equals(ModItems.ZANPAKUTO.get()))
                {
                    ZanpakutoItem zanpakutoItem = (ZanpakutoItem) player.getMainHandItem().getItem();
                    switch (message.stateNumber)
                    {
                        case 0:
                            zanpakutoItem.setZanpakutoState(ZanpakutoItem.STATE.SEALED);
                            break;
                        case 1:
                            zanpakutoItem.setZanpakutoState(ZanpakutoItem.STATE.SHIKAI);
                            break;
                        case 2:
                            zanpakutoItem.setZanpakutoState(ZanpakutoItem.STATE.BANKAI);
                            break;
                    }
                }
            });
        }
        contextSupplier.get().setPacketHandled(true);
    }
}

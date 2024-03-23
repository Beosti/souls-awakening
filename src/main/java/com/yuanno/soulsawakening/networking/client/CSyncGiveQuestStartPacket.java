package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.stats.HakudaGainEvent;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncGiveQuestStartPacket {

    private String data;

    public CSyncGiveQuestStartPacket() {}

    public CSyncGiveQuestStartPacket(Quest quest)
    {
        String id = quest.getRegistryName().toString();
        this.data = id;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUtf(this.data);
    }

    public static CSyncGiveQuestStartPacket decode(PacketBuffer buffer)
    {
        CSyncGiveQuestStartPacket msg = new CSyncGiveQuestStartPacket();
        msg.data = buffer.readUtf();
        return msg;
    }

    public static void handle(CSyncGiveQuestStartPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                Quest quest = GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(message.data));
                if (quest.getQuestStart() != null)
                    quest.getQuestStart().giveStart(player);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}

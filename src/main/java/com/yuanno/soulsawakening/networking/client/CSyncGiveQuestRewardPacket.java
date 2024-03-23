package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.quests.Quest;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSyncGiveQuestRewardPacket {

    private String data;

    public CSyncGiveQuestRewardPacket() {}

    public CSyncGiveQuestRewardPacket(Quest quest)
    {
        String id = quest.getRegistryName().toString();
        this.data = id;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeUtf(this.data);
    }

    public static CSyncGiveQuestRewardPacket decode(PacketBuffer buffer)
    {
        CSyncGiveQuestRewardPacket msg = new CSyncGiveQuestRewardPacket();
        msg.data = buffer.readUtf();
        return msg;
    }

    public static void handle(CSyncGiveQuestRewardPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                Quest quest = GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation(message.data));
                if (quest.getQuestReward() != null)
                    quest.getQuestReward().giveReward(player);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}

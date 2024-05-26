package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.events.UpdateStatEvent;
import com.yuanno.soulsawakening.init.ModAdvancements;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSAchievementSpiritWeaponPacket {

    private INBT data;

    public CSAchievementSpiritWeaponPacket() {}


    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt((CompoundNBT) data);
    }

    public static CSAchievementSpiritWeaponPacket decode(PacketBuffer buffer)
    {
        CSAchievementSpiritWeaponPacket msg = new CSAchievementSpiritWeaponPacket();
        msg.data = buffer.readNbt();
        return msg;
    }

    public static void handle(CSAchievementSpiritWeaponPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                ModAdvancements.SPIRIT_WEAPON.trigger((ServerPlayerEntity) player);
            });
        }
        ctx.get().setPacketHandled(true);
    }

}

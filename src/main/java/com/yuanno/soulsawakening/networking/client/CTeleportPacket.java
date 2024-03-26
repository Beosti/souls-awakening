package com.yuanno.soulsawakening.networking.client;

import com.yuanno.soulsawakening.teleport.TeleportPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CTeleportPacket {

    TeleportPosition teleportPosition;
    public CTeleportPacket()
    {

    }

    public CTeleportPacket(TeleportPosition teleportPosition)
    {
        this.teleportPosition = teleportPosition;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeNbt(this.teleportPosition.save());
    }

    public static CTeleportPacket decode(PacketBuffer buffer)
    {
        CTeleportPacket msg = new CTeleportPacket();
        CompoundNBT compoundNBT = buffer.readNbt();
        TeleportPosition teleportPosition = new TeleportPosition();
        teleportPosition.load(compoundNBT);
        msg.teleportPosition = teleportPosition;
        return msg;
    }

    public static void handle(CTeleportPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        if (ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                TeleportPosition teleportPosition = message.teleportPosition;
                System.out.println(teleportPosition.getDimension());
                System.out.println(player.level.dimension());
                if (!teleportPosition.getDimension().equals(player.level.dimension().toString()))
                {
                    player.sendMessage(new TranslationTextComponent("Not in the right dimension!"), Util.NIL_UUID);
                    return;
                }
                BlockPos blockPos = teleportPosition.getBlockPos();
                player.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            });
        }
        ctx.get().setPacketHandled(true);
    }
}

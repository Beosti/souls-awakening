package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.CChangeZanpakutoStatePacket;
import com.yuanno.soulsawakening.networking.client.COpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.client.CSyncentityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SOpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;

public class ModNetwork {

    public static void init()
    {
        PacketHandler.registerPacket(CSyncentityStatsPacket.class, CSyncentityStatsPacket::encode, CSyncentityStatsPacket::decode, CSyncentityStatsPacket::handle);
        PacketHandler.registerPacket(COpenPlayerScreenPacket.class, COpenPlayerScreenPacket::encode, COpenPlayerScreenPacket::decode, COpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(CChangeZanpakutoStatePacket.class, CChangeZanpakutoStatePacket::encode, CChangeZanpakutoStatePacket::decode, CChangeZanpakutoStatePacket::handle);



        PacketHandler.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
        PacketHandler.registerPacket(SOpenPlayerScreenPacket.class, SOpenPlayerScreenPacket::encode, SOpenPlayerScreenPacket::decode, SOpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);

    }
}

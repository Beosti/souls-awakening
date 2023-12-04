package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import com.yuanno.soulsawakening.networking.server.SOpenPlayerScreenPacket;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import com.yuanno.soulsawakening.networking.server.SSyncMiscDataPacket;

public class ModNetwork {

    public static void init()
    {
        PacketHandler.registerPacket(CSyncentityStatsPacket.class, CSyncentityStatsPacket::encode, CSyncentityStatsPacket::decode, CSyncentityStatsPacket::handle);
        PacketHandler.registerPacket(COpenPlayerScreenPacket.class, COpenPlayerScreenPacket::encode, COpenPlayerScreenPacket::decode, COpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(CChangeZanpakutoStatePacket.class, CChangeZanpakutoStatePacket::encode, CChangeZanpakutoStatePacket::decode, CChangeZanpakutoStatePacket::handle);
        PacketHandler.registerPacket(CSyncMiscDataPacket.class, CSyncMiscDataPacket::encode, CSyncMiscDataPacket::decode, CSyncMiscDataPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsStatsPacket.class, CSyncentityStatsStatsPacket::encode, CSyncentityStatsStatsPacket::decode, CSyncentityStatsStatsPacket::handle);
        PacketHandler.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(CRightClickEmptyPacket.class, CRightClickEmptyPacket::encode, CRightClickEmptyPacket::decode, CRightClickEmptyPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsZanjutsuPacket.class, CSyncentityStatsZanjutsuPacket::encode, CSyncentityStatsZanjutsuPacket::decode, CSyncentityStatsZanjutsuPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsHakudaPacket.class, CSyncentityStatsHakudaPacket::encode, CSyncentityStatsHakudaPacket::decode, CSyncentityStatsHakudaPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsHohoPacket.class, CSyncentityStatsHohoPacket::encode, CSyncentityStatsHohoPacket::decode, CSyncentityStatsHohoPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsHollowPacket.class, CSyncentityStatsHollowPacket::encode, CSyncentityStatsHollowPacket::decode, CSyncentityStatsHollowPacket::handle);



        PacketHandler.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
        PacketHandler.registerPacket(SOpenPlayerScreenPacket.class, SOpenPlayerScreenPacket::encode, SOpenPlayerScreenPacket::decode, SOpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(SSyncMiscDataPacket.class, SSyncMiscDataPacket::encode, SSyncMiscDataPacket::decode, SSyncMiscDataPacket::handle);

    }
}

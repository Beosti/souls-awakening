package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.client.*;
import com.yuanno.soulsawakening.networking.server.*;

public class ModNetwork {

    public static void init()
    {
        PacketHandler.registerPacket(CSyncentityStatsPacket.class, CSyncentityStatsPacket::encode, CSyncentityStatsPacket::decode, CSyncentityStatsPacket::handle);
        PacketHandler.registerPacket(COpenPlayerScreenPacket.class, COpenPlayerScreenPacket::encode, COpenPlayerScreenPacket::decode, COpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(CChangeZanpakutoStatePacket.class, CChangeZanpakutoStatePacket::encode, CChangeZanpakutoStatePacket::decode, CChangeZanpakutoStatePacket::handle);
        PacketHandler.registerPacket(CSyncMiscDataPacket.class, CSyncMiscDataPacket::encode, CSyncMiscDataPacket::decode, CSyncMiscDataPacket::handle);
        PacketHandler.registerPacket(CSyncAbilityDataPacket.class, CSyncAbilityDataPacket::encode, CSyncAbilityDataPacket::decode, CSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(CRightClickEmptyPacket.class, CRightClickEmptyPacket::encode, CRightClickEmptyPacket::decode, CRightClickEmptyPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsZanjutsuPacket.class, CSyncentityStatsZanjutsuPacket::encode, CSyncentityStatsZanjutsuPacket::decode, CSyncentityStatsZanjutsuPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsHakudaPacket.class, CSyncentityStatsHakudaPacket::encode, CSyncentityStatsHakudaPacket::decode, CSyncentityStatsHakudaPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsHohoPacket.class, CSyncentityStatsHohoPacket::encode, CSyncentityStatsHohoPacket::decode, CSyncentityStatsHohoPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsHollowPacket.class, CSyncentityStatsHollowPacket::encode, CSyncentityStatsHollowPacket::decode, CSyncentityStatsHollowPacket::handle);
        PacketHandler.registerPacket(CHollowEvolutionPacket.class, CHollowEvolutionPacket::encode, CHollowEvolutionPacket::decode, CHollowEvolutionPacket::handle);
        PacketHandler.registerPacket(CStartChallengePacket.class, CStartChallengePacket::encode, CStartChallengePacket::decode, CStartChallengePacket::handle);
        PacketHandler.registerPacket(COpenChallengeScreenPacket.class, COpenChallengeScreenPacket::encode, COpenChallengeScreenPacket::decode, COpenChallengeScreenPacket::handle);
        PacketHandler.registerPacket(CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
        PacketHandler.registerPacket(COpenTradingScreenPacket.class, COpenTradingScreenPacket::encode, COpenTradingScreenPacket::decode, COpenTradingScreenPacket::handle);
        PacketHandler.registerPacket(CSyncentityStatsReiatsuPacket.class, CSyncentityStatsReiatsuPacket::encode, CSyncentityStatsReiatsuPacket::decode, CSyncentityStatsReiatsuPacket::handle);
        PacketHandler.registerPacket(COpenAbilityListScreenPacket.class, COpenAbilityListScreenPacket::encode, COpenAbilityListScreenPacket::decode, COpenAbilityListScreenPacket::handle);
        PacketHandler.registerPacket(CUseSpellPacket.class, CUseSpellPacket::encode, CUseSpellPacket::decode, CUseSpellPacket::handle);
        PacketHandler.registerPacket(CSyncQuestDataPacket.class, CSyncQuestDataPacket::encode, CSyncQuestDataPacket::decode, CSyncQuestDataPacket::handle);



        PacketHandler.registerPacket(SSyncEntityStatsPacket.class, SSyncEntityStatsPacket::encode, SSyncEntityStatsPacket::decode, SSyncEntityStatsPacket::handle);
        PacketHandler.registerPacket(SOpenPlayerScreenPacket.class, SOpenPlayerScreenPacket::encode, SOpenPlayerScreenPacket::decode, SOpenPlayerScreenPacket::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
        PacketHandler.registerPacket(SSyncMiscDataPacket.class, SSyncMiscDataPacket::encode, SSyncMiscDataPacket::decode, SSyncMiscDataPacket::handle);
        PacketHandler.registerPacket(SSyncDynDimensionsPacket.class, SSyncDynDimensionsPacket::encode, SSyncDynDimensionsPacket::decode, SSyncDynDimensionsPacket::handle);
        PacketHandler.registerPacket(SSyncChallengeDataPacket.class, SSyncChallengeDataPacket::encode, SSyncChallengeDataPacket::decode, SSyncChallengeDataPacket::handle);
        PacketHandler.registerPacket(SOpenChallengeScreenPacket.class, SOpenChallengeScreenPacket::encode, SOpenChallengeScreenPacket::decode, SOpenChallengeScreenPacket::handle);
        PacketHandler.registerPacket(SOpenTradingScreenPacket.class, SOpenTradingScreenPacket::encode, SOpenTradingScreenPacket::decode, SOpenTradingScreenPacket::handle);
        PacketHandler.registerPacket(SOpenAbilityScreenPacket.class, SOpenAbilityScreenPacket::encode, SOpenAbilityScreenPacket::decode, SOpenAbilityScreenPacket::handle);
        PacketHandler.registerPacket(SSyncQuestDataPacket.class, SSyncQuestDataPacket::encode, SSyncQuestDataPacket::decode, SSyncQuestDataPacket::handle);

    }
}

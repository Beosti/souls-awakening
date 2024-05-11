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
        PacketHandler.registerPacket(CHollowEvolutionPacket.class, CHollowEvolutionPacket::encode, CHollowEvolutionPacket::decode, CHollowEvolutionPacket::handle);
        PacketHandler.registerPacket(CStartChallengePacket.class, CStartChallengePacket::encode, CStartChallengePacket::decode, CStartChallengePacket::handle);
        PacketHandler.registerPacket(COpenChallengeScreenPacket.class, COpenChallengeScreenPacket::encode, COpenChallengeScreenPacket::decode, COpenChallengeScreenPacket::handle);
        PacketHandler.registerPacket(CGiveItemStackPacket.class, CGiveItemStackPacket::encode, CGiveItemStackPacket::decode, CGiveItemStackPacket::handle);
        PacketHandler.registerPacket(COpenTradingScreenPacket.class, COpenTradingScreenPacket::encode, COpenTradingScreenPacket::decode, COpenTradingScreenPacket::handle);
        PacketHandler.registerPacket(COpenAbilityListScreenPacket.class, COpenAbilityListScreenPacket::encode, COpenAbilityListScreenPacket::decode, COpenAbilityListScreenPacket::handle);
        PacketHandler.registerPacket(CUseSpellPacket.class, CUseSpellPacket::encode, CUseSpellPacket::decode, CUseSpellPacket::handle);
        PacketHandler.registerPacket(CSyncQuestDataPacket.class, CSyncQuestDataPacket::encode, CSyncQuestDataPacket::decode, CSyncQuestDataPacket::handle);
        PacketHandler.registerPacket(COpenQuestScreenPacket.class, COpenQuestScreenPacket::encode, COpenQuestScreenPacket::decode, COpenQuestScreenPacket::handle);
        PacketHandler.registerPacket(COpenChatPromptScreenPacket.class, COpenChatPromptScreenPacket::encode, COpenChatPromptScreenPacket::decode, COpenChatPromptScreenPacket::handle);
        PacketHandler.registerPacket(CSyncGiveQuestStartPacket.class, CSyncGiveQuestStartPacket::encode, CSyncGiveQuestStartPacket::decode, CSyncGiveQuestStartPacket::handle);
        PacketHandler.registerPacket(CSyncGiveQuestRewardPacket.class, CSyncGiveQuestRewardPacket::encode, CSyncGiveQuestRewardPacket::decode, CSyncGiveQuestRewardPacket::handle);
        PacketHandler.registerPacket(CSyncTeleportPacket.class, CSyncTeleportPacket::encode, CSyncTeleportPacket::decode, CSyncTeleportPacket::handle);
        PacketHandler.registerPacket(COpenTeleportScreenPacket.class, COpenTeleportScreenPacket::encode, COpenTeleportScreenPacket::decode, COpenTeleportScreenPacket::handle);
        PacketHandler.registerPacket(CTeleportPacket.class, CTeleportPacket::encode, CTeleportPacket::decode, CTeleportPacket::handle);
        PacketHandler.registerPacket(CSAchievementSpiritWeaponPacket.class, CSAchievementSpiritWeaponPacket::encode, CSAchievementSpiritWeaponPacket::decode, CSAchievementSpiritWeaponPacket::handle);



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
        PacketHandler.registerPacket(SOpenQuestScreenPacket.class, SOpenQuestScreenPacket::encode, SOpenQuestScreenPacket::decode, SOpenQuestScreenPacket::handle);
        PacketHandler.registerPacket(SOpenChatPromptScreenPacket.class, SOpenChatPromptScreenPacket::encode, SOpenChatPromptScreenPacket::decode, SOpenChatPromptScreenPacket::handle);
        PacketHandler.registerPacket(SSyncTeleportPacket.class, SSyncTeleportPacket::encode, SSyncTeleportPacket::decode, SSyncTeleportPacket::handle);
        PacketHandler.registerPacket(SOpenTeleportScreenPacket.class, SOpenTeleportScreenPacket::encode, SOpenTeleportScreenPacket::decode, SOpenTeleportScreenPacket::handle);
        PacketHandler.registerPacket(SOpenWeaponChoiceScreenPacket.class, SOpenWeaponChoiceScreenPacket::encode, SOpenWeaponChoiceScreenPacket::decode, SOpenWeaponChoiceScreenPacket::handle);
        PacketHandler.registerPacket(SOpenCommandScreenPacket.class, SOpenCommandScreenPacket::encode, SOpenCommandScreenPacket::decode, SOpenCommandScreenPacket::handle);

    }
}

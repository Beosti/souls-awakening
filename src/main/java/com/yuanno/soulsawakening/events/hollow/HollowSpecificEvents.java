package com.yuanno.soulsawakening.events.hollow;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.abilities.hollow.CeroAbility;
import com.yuanno.soulsawakening.abilities.hollow.GargantaAbility;
import com.yuanno.soulsawakening.data.ability.AbilityDataCapability;
import com.yuanno.soulsawakening.data.ability.IAbilityData;
import com.yuanno.soulsawakening.data.entity.EntityStatsCapability;
import com.yuanno.soulsawakening.data.entity.IEntityStats;
import com.yuanno.soulsawakening.init.ModValues;
import com.yuanno.soulsawakening.networking.PacketHandler;
import com.yuanno.soulsawakening.networking.server.SSyncAbilityDataPacket;
import com.yuanno.soulsawakening.networking.server.SSyncEntityStatsPacket;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class HollowSpecificEvents {
    public static final UUID GILLIAN_ATTACK_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");
    public static final UUID ADJUCHA_SPEED_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");
    public static final UUID VASTO_LORDE_ATTACK_SPEED_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");
    public static final UUID VASTO_LORDE_ATTACK_BONUS_ID = UUID.fromString("e19608b6-8b16-11ee-b9d1-0242ac120002");

    @SubscribeEvent
    public static void hollowEvolutionEvent(HollowEvolutionEvent event)
    {
        PlayerEntity player = event.getPlayer();
        if (player.level.isClientSide)
            return;
        IEntityStats entityStats = EntityStatsCapability.get(player);
        IAbilityData abilityData = AbilityDataCapability.get(player);
        AttributeModifier attributeModifierGillian = new AttributeModifier("Gillian attack bonus", 5, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierAdjucha = new AttributeModifier("Adjucha Speed Bonus", 0.4, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierVastoLordeAttackSpeed = new AttributeModifier("Vasto Lorde Attack Speed Bonus", 1, AttributeModifier.Operation.ADDITION);
        AttributeModifier attributeModifierVastoLordeAttack = new AttributeModifier("Vasto Lorde Attack Bonus", 5, AttributeModifier.Operation.ADDITION);

        switch (entityStats.getRank()) {
            case (ModValues.BASE):
                entityStats.setRank(ModValues.GILLIAN);
                abilityData.addUnlockedAbility(CeroAbility.INSTANCE);
                player.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(attributeModifierGillian);
                break;
                case (ModValues.GILLIAN):
                    entityStats.setRank(ModValues.ADJUCHA);
                    abilityData.addUnlockedAbility(GargantaAbility.INSTANCE);
                    player.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(attributeModifierAdjucha);
                    break;
                    case (ModValues.ADJUCHA):
                        entityStats.setRank(ModValues.VASTO_LORDE);
                        player.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(attributeModifierVastoLordeAttack);
                        player.getAttribute(Attributes.ATTACK_SPEED).addPermanentModifier(attributeModifierVastoLordeAttackSpeed);
                        break;
                        /*
                        case (ModValues.VASTO_LORDE):
                            entityStats.setRank(ModValues.ARRANCAR);
                            break;

                         */
            }
            entityStats.setHollowPoints(0);
            PacketHandler.sendTo(new SSyncEntityStatsPacket(player.getId(), entityStats), player);
            PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityData), player);
    }
}

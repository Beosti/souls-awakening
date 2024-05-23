package com.yuanno.soulsawakening.items;

import com.yuanno.soulsawakening.Main;
import com.yuanno.soulsawakening.init.ModAttributes;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

public class ZanpakutoWakizashiItem extends ZanpakutoItem {

    public ZanpakutoWakizashiItem() {
        super(4, -1.8f);
    }

    @Mod.EventBusSubscriber(modid = Main.MODID)
    public static class WakizaShiEvents
    {
        public static final UUID WAKIZASHI_ATTACK_SPEED_ID = UUID.fromString("d85b324a-8b2e-11ee-b9d1-0242ac120002");

        @SubscribeEvent
        public static void wakizashiBonus(ItemAttributeModifierEvent event)
        {
            ItemStack itemStack = event.getItemStack();
            if (event.getSlotType() != EquipmentSlotType.MAINHAND)
                return;
            if (itemStack.getItem().asItem() instanceof ZanpakutoWakizashiItem)
            {
                AttributeModifier mod = new AttributeModifier(WAKIZASHI_ATTACK_SPEED_ID, "Wakizashi Bonus", -0.3, AttributeModifier.Operation.ADDITION);

                event.addModifier(ModAttributes.ATTACK_RANGE.get(), mod);
            }
        }
    }
}

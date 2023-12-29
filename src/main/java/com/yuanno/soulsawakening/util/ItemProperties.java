package com.yuanno.soulsawakening.util;

import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.init.ModValues;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class ItemProperties {

    private static final IItemPropertyGetter ZANPAKUTO_STAGE = (itemStack, world, livingEntity) ->
    {

        if (itemStack.getTag().getString("zanpakutoState").equals(ModValues.STATE.SEALED.name()))
            return 1;
        else if (itemStack.getTag().getString("zanpakutoState").equals(ModValues.STATE.SHIKAI.name()))
            return 2;
        else if (itemStack.getTag().getString("zanpakutoState").equals(ModValues.STATE.BANKAI.name()))
            return 3;
        else
            return 1;
    };
    private static final IItemPropertyGetter ZANPAKUTO_ELEMENT = (itemStack, world, livingEntity) ->
    {
        String zanpakutoElement = itemStack.getTag().getString("zanpakutoElement");
        if (zanpakutoElement.equals(ModValues.STATE.SEALED.name()))
            return 0;
        switch (zanpakutoElement)
        {
            case ("DARK"):
                return 1;
            case ("FIRE"):
                return 2;
            case ("HEAL"):
                return 3;
            case ("LIGHTNING"):
                return 4;
            case ("LUNAR"):
                return 5;
            case ("NORMAL"):
                return 6;
            case ("POISON"):
                return 7;
            case ("WATER"):
                return 8;
            case ("WIND"):
                return 9;
            case ("SHINSO"):
                return 10;
            default:
                return 0;
        }
    };
    private static final IItemPropertyGetter ZANPAKUTO_TYPE = (itemStack, world, livingEntity) ->
    {
        String zanpakutoElement = itemStack.getTag().getString("zanpakutoType");
        switch (zanpakutoElement)
        {
            case ("TYPE_1"):
                return 1;
            case ("TYPE_2"):
                return 2;
            default:
                return 1;
        }
    };
    public static void register()
    {
        ItemModelsProperties.register(ModItems.ZANPAKUTO.get(), new ResourceLocation("stage"), ZANPAKUTO_STAGE);
        ItemModelsProperties.register(ModItems.ZANPAKUTO.get(), new ResourceLocation("element"), ZANPAKUTO_ELEMENT);
        ItemModelsProperties.register(ModItems.ZANPAKUTO.get(), new ResourceLocation("type"), ZANPAKUTO_TYPE);

        ItemModelsProperties.register(ModItems.ZANPAKUTO_WAKIZASHI.get(), new ResourceLocation("stage"), ZANPAKUTO_STAGE);
        ItemModelsProperties.register(ModItems.ZANPAKUTO_WAKIZASHI.get(), new ResourceLocation("element"), ZANPAKUTO_ELEMENT);
        ItemModelsProperties.register(ModItems.ZANPAKUTO_WAKIZASHI.get(), new ResourceLocation("type"), ZANPAKUTO_TYPE);

    }
}

package com.yuanno.soulsawakening.util;

import com.yuanno.soulsawakening.init.ModItems;
import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;

public class ItemProperties {

    private static final IItemPropertyGetter ZANPAKUTO_STAGE = (itemStack, world, livingEntity) ->
    {
        ZanpakutoItem zanpakutoItem = (ZanpakutoItem) itemStack.getItem();
        switch (zanpakutoItem.getZanpakutoState())
        {
            case SEALED:
                return 1;
            case SHIKAI:
                return 2;
            case BANKAI:
                return 3;
            default:
                return 1;
        }
    };
    private static final IItemPropertyGetter ZANPAKUTO_ELEMENT = (itemStack, world, livingEntity) ->
    {
        ZanpakutoItem zanpakutoItem = (ZanpakutoItem) itemStack.getItem();
        if (zanpakutoItem.getZanpakutoState().equals(ZanpakutoItem.STATE.SEALED))
            return 0;
        switch (zanpakutoItem.getZanpakutoElement())
        {
            case FIRE:
                return 1;
            case POISON:
                return 2;
            default:
                return 0;
        }
    };
    private static final IItemPropertyGetter ZANPAKUTO_TYPE = (itemStack, world, livingEntity) ->
    {
        ZanpakutoItem zanpakutoItem = (ZanpakutoItem) itemStack.getItem();
        if (zanpakutoItem.getZanpakutoType().equals(ZanpakutoItem.TYPE.TYPE_1))
            return 0;
        switch (zanpakutoItem.getZanpakutoType())
        {
            case TYPE_1:
                return 1;
            case TYPE_2:
                return 2;
            case TYPE_3:
                return 3;
            default:
                return 0;
        }
    };
    public static void register()
    {
        ItemModelsProperties.register(ModItems.ZANPAKUTO.get(), new ResourceLocation("stage"), ZANPAKUTO_STAGE);
        ItemModelsProperties.register(ModItems.ZANPAKUTO.get(), new ResourceLocation("element"), ZANPAKUTO_ELEMENT);
        ItemModelsProperties.register(ModItems.ZANPAKUTO.get(), new ResourceLocation("type"), ZANPAKUTO_TYPE);

    }
}

package com.yuanno.soulsawakening.api;

import com.yuanno.soulsawakening.Main;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;
import java.util.function.Supplier;

public class Beapi {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }

    public static String getResourceName(String text)
    {
        return text
                .replaceAll("[ \\t]+$", "")
                .replaceAll("\\(","")
                .replaceAll("\\)","")
                .replaceAll("\\s+", "_")
                .replaceAll("[\\'\\:\\-\\,\\#]", "")
                .replaceAll("\\&", "and").toLowerCase();
    }


}

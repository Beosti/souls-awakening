package com.yuanno.soulsawakening.init;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> zanpakuto_unlock;
    public static final ForgeConfigSpec.ConfigValue<Integer> zanpakuto_zanjutsu;
    public static final ForgeConfigSpec.ConfigValue<Integer> spirit_chain;
    public static final ForgeConfigSpec.ConfigValue<Integer> stat_limit;
    public static final ForgeConfigSpec.ConfigValue<Integer> hollow_evolution;

    static {
        BUILDER.push("Config for souls awakening");

        String stringZanpakutoUnlock = String.format("Handles the way you get your element for your shikai\n" +
                "%s: Killing hollows to earn element points that influence what you get\n" +
                "%s: Completely random, do not need to kill hollows for it", ModConfigValues.KILLHOLLOW, ModConfigValues.RANDOM);
        zanpakuto_unlock = BUILDER.comment(stringZanpakutoUnlock).define("Zanpakuto path way", ModConfigValues.KILLHOLLOW);
        zanpakuto_zanjutsu = BUILDER.comment("This handles the amount of zanjutsu you need to activate your shikai as shinigami").define("Zanpakuto zanjutsu needed", 20);
        spirit_chain = BUILDER.comment("Amount of time a spirit has until it becomes a hollow in seconds").define("spirit chain time", 400);
        stat_limit = BUILDER.comment("Amount of maximal stats you can have in total").define("max total stats", 100);
        hollow_evolution = BUILDER.comment("Needed hollow points to evolve to the next stage of hollow").define("hollow points for evolution", 50);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static class ModConfigValues {

        public static final String KILLHOLLOW = "KILL-HOLLOW";
        public static final String RANDOM = "RANDOM";
    }
}

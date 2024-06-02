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
    public static final ForgeConfigSpec.ConfigValue<Integer> APE_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SPIDER_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> THORNS_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> FLYING_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> GOLEM_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> PLUS_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> SHINIGAMI_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> TRADER_RATE;
    public static final ForgeConfigSpec.ConfigValue<Integer> HOLLOW_FOLLOWRANGE;


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

        // spawn rates
        APE_RATE = BUILDER.comment("Spawn rate for the ape hollow").define("Spawn rate: ape hollow", 5);
        SPIDER_RATE = BUILDER.comment("Spawn rate for the spider hollow").define("Spawn rate: spider hollow", 5);
        THORNS_RATE = BUILDER.comment("Spawn rate for the thorns hollow").define("Spawn rate: thorns hollow", 5);
        FLYING_RATE = BUILDER.comment("Spawn rate for the flying hollow").define("Spawn rate: flying hollow", 5);
        GOLEM_RATE = BUILDER.comment("Spawn rate for the golem hollow").define("Spawn rate: golem hollow", 5);

        PLUS_RATE = BUILDER.comment("Spawn rate for pluses").define("Spawn rate: pluses", 20);
        SHINIGAMI_RATE = BUILDER.comment("Spawn rate for shinigami").define("Spawn rate: shinigami", 20);
        TRADER_RATE = BUILDER.comment("Spawn rate for traders").define("Spawn rate: traders", 10);

        HOLLOW_FOLLOWRANGE = BUILDER.comment("Range hollows will follow and find entities for").define("Follow range for hollows", 50);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    public static class ModConfigValues {

        public static final String KILLHOLLOW = "KILL-HOLLOW";
        public static final String RANDOM = "RANDOM";
    }
}

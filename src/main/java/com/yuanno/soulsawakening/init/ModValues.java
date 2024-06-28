package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ModValues {

    // ZANPAKUTO STYLES
    public static List<String> zanpakuto_styles = new ArrayList<>(Arrays.asList(
       "basic", "basic_right"
    ));

    // RACES
    public static final String HUMAN = "Human";
    public static final String SPIRIT = "Spirit";
    public static final String HOLLOW = "Hollow";
    public static final String WHOLE = "Whole";
    public static final String SHINIGAMI = "Shinigami";
    public static final String QUINCY = "Quincy";
    public static final String FULLBRINGER = "Fullbringer";

    // RANK HOLLOW
    public static final String BASE = "Base";
    public static final String GILLIAN = "Gillian";
    public static final String ADJUCHA = "Adjucha";
    public static final String VASTO_LORDE = "Vasto Lorde";
    public static final String ARRANCAR = "Arrancar";
    public static List<String> rankHollow = new ArrayList<>(Arrays.asList(
            ModValues.BASE, ModValues.GILLIAN, ModValues.ADJUCHA, ModValues.VASTO_LORDE, ModValues.ARRANCAR
    ));
    // RANK SHINIGAMI
    public static final String NON_OFFICER = "Non-Officer";
    public static final String SEATED_OFFICER_20 = "Seated officer 20";
    public static final String SEATED_OFFICER_19 = "Seated officer 20";
    public static final String SEATED_OFFICER_18 = "Seated officer 20";
    public static final String SEATED_OFFICER_17 = "Seated officer 20";
    public static final String SEATED_OFFICER_16 = "Seated officer 20";
    public static final String SEATED_OFFICER_15 = "Seated officer 20";
    public static final String SEATED_OFFICER_14 = "Seated officer 20";
    public static final String SEATED_OFFICER_13 = "Seated officer 20";
    public static final String SEATED_OFFICER_12 = "Seated officer 12";
    public static final String SEATED_OFFICER_11 = "Seated officer 11";
    public static final String SEATED_OFFICER_10 = "Seated officer 10";
    public static final String SEATED_OFFICER_9 = "Seated officer 9";
    public static final String SEATED_OFFICER_8 = "Seated officer 8";
    public static final String SEATED_OFFICER_7 = "Seated officer 7";
    public static final String SEATED_OFFICER_6 = "Seated officer 6";
    public static final String SEATED_OFFICER_5 = "Seated officer 5";
    public static final String SEATED_OFFICER_4 = "Seated officer 4";
    public static final String SEATED_OFFICER_3 = "Seated officer 3";
    public static final String VICE_CAPTAIN = "Vice Captain";
    public static final String CAPTAIN = "Captain";
    public static List<String> rankShinigami = new ArrayList<>(Arrays.asList(
            ModValues.NON_OFFICER, ModValues.SEATED_OFFICER_20, ModValues.SEATED_OFFICER_19, ModValues.SEATED_OFFICER_18, ModValues.SEATED_OFFICER_17,
            ModValues.SEATED_OFFICER_16, ModValues.SEATED_OFFICER_15, ModValues.SEATED_OFFICER_14, ModValues.SEATED_OFFICER_13,
            ModValues.SEATED_OFFICER_12, ModValues.SEATED_OFFICER_11, ModValues.SEATED_OFFICER_10, ModValues.SEATED_OFFICER_9, ModValues.SEATED_OFFICER_8,
            ModValues.SEATED_OFFICER_7, ModValues.SEATED_OFFICER_6,ModValues.SEATED_OFFICER_5,ModValues.SEATED_OFFICER_4,ModValues.SEATED_OFFICER_3,
            ModValues.VICE_CAPTAIN, ModValues.CAPTAIN
            ));
    // ELEMENTS
    public static final String DARK = "dark";
    public static final String FIRE = "fire";
    public static final String HEAL = "heal";
    public static final String LIGHTNING = "lightning";
    public static final String LUNAR = "lunar";
    public static final String NORMAL = "normal";
    public static final String POISON = "poison";
    public static final String WATER = "water";
    public static final String WIND = "wind";

    // SPECIFIC
    public static final String SHINSO = "shinso";

    public static final int MAX_DIFFICULTY_STARS = 15;
    public enum ELEMENT {
        NONE, DARK, FIRE, HEAL, LIGHTNING, LUNAR, NORMAL, POISON, WATER, WIND;

        public static ELEMENT getRandomElement() {
            Random random = new Random();
            ELEMENT[] elements = values();

            // Exclude NONE from the random selection
            int index;
            do {
                index = random.nextInt(elements.length);
            } while (elements[index] == NONE);

            return elements[index];
        }

        public static String getRandomElementString() {
            Random random = new Random();
            ELEMENT[] elements = values();

            // Exclude NONE from the random selection
            int index;
            do {
                index = random.nextInt(elements.length);
            } while (elements[index] == NONE);

            return elements[index].toString().toLowerCase();
        }
    }

    public enum STATE {
        ASAUCHI, SEALED, SHIKAI, BANKAI;
        public static STATE getRandomState()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public enum TYPE {
        TYPE_1, TYPE_2;

        public static TYPE getRandomType()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}

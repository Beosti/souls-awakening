package com.yuanno.soulsawakening.init;

import com.yuanno.soulsawakening.items.blueprints.ZanpakutoItem;

import java.util.Random;

public class ModValues {

    // RACES
    public static final String HUMAN = "Human";
    public static final String SPIRIT = "Spirit";
    public static final String HOLLOW = "Hollow";
    public static final String WHOLE = "Whole";
    public static final String SHINIGAMI = "Shinigami";
    public static final String QUINCY = "Quincy";
    public static final String FULLBRINGER = "Fullbringer";

    // RANK
    public static final String BASE = "Base";
    public static final String GILLIAN = "Gillian";
    public static final String ADJUCHA = "Adjucha";
    public static final String VASTO_LORDE = "Vasto Lorde";
    public static final String ARRANCAR = "Arrancar";

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

    public enum ELEMENT {
        NONE, DARK, FIRE, HEAL, LIGHTNING, LUNAR, NORMAL, POISON, WATER, WIND, SHINSO;

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
    }

    public enum STATE {
        SEALED, SHIKAI, BANKAI;
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

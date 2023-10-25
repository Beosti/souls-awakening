package com.yuanno.soulsawakening.init;

import java.util.Random;

public class ModResources {

    public enum STATE {
        SEALED, SHIKAI, BANKAI;
        public static STATE getRandomState()
        {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }
}

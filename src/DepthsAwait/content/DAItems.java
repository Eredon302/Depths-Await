package DepthsAwait.content;

import mindustry.type.Item;

public class DAItems {

    public static Item rhodite, thaumium, magnetite;

    public static void load(){

        rhodite = new Item("rhodite"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};
        thaumium = new Item("thaumium"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};
        magnetite = new Item("magnetite"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};

    }
}

package DepthsAwait.content;

import mindustry.type.Item;

public class DAItems {

    public static Item rhodite, thaumium, lithium;
    //TODO maybe also add magnetite for something and also figure out how to color item filters

    public static void load(){

        rhodite = new Item("rhodite"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};
        thaumium = new Item("thaumium"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};
        lithium = new Item("lithium"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};

    }
}

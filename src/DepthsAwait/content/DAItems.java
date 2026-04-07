package DepthsAwait.content;

import mindustry.type.Item;

public class DAItems {

    public static Item rhodite;
    public static Item thaumium;
    public static Item basalticResidue;
    public static Item lithium;
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

        basalticResidue = new Item("basalticResidue"){{
           alwaysUnlocked = true;
        }};

        lithium = new Item("lithium"){{
            cost = 1f;
            alwaysUnlocked = true;
        }};

    }
}

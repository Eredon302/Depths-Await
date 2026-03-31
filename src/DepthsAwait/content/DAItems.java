package DepthsAwait.content;

import mindustry.type.Item;

public class DAItems {

    public static Item rhodite, thaumium, magnetite;

    public static void load(){

        rhodite = new Item("rhodite"){{
            cost = 1f;
        }};
        thaumium = new Item("thaumium"){{
            cost = 1f;
        }};
        magnetite = new Item("magnetite"){{
            cost = 1f;
        }};

    }
}

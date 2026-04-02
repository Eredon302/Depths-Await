package DepthsAwait.content;

import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.type.Planet;

public class DAPlanets {

    public static Planet cyria;

    public static void load(){

        cyria = new Planet("cyria", Planets.sun, 1f, 2){{
            visible = true;
            accessible = true;
            alwaysUnlocked = true;
            //iconColor =
        }};

    }

}

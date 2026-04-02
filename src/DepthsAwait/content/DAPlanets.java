package DepthsAwait.content;

import mindustry.content.Blocks;
import mindustry.content.Planets;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.type.Planet;

public class DAPlanets {

    public static Planet cyria;

    public static void load(){

        cyria = new Planet("cyria", Planets.sun, 1f, 1){{

            visible = true;
            accessible = true;
            alwaysUnlocked = true;
            //iconColor =

            generator = new PlanetGenerator(){{
                floor = Blocks.stone;
            }};

        }};

    }

}

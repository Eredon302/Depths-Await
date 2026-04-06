package DepthsAwait.content;

import arc.graphics.Color;
import mindustry.type.Liquid;

import static mindustry.content.StatusEffects.*;

public class DALiquids {

    public static Liquid magma;

    public static void load(){

        magma = new Liquid("Magma"){{
            color = Color.valueOf("ff4800");
            barColor = Color.valueOf("ff4800");
            lightColor = Color.valueOf("ff480080");

            temperature = 1;
            heatCapacity = 0;
            viscosity = 1;
            coolant = false;
            effect = melting;
        }};

    }

}

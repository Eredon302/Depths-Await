package DepthsAwait.content;

import mindustry.gen.UnitEntity;
import mindustry.type.UnitType;

public class DAUnits {

    //Core units
    public static UnitType icicle;
    //Other units

    public static void load(){

        icicle = new UnitType("icicle"){{
            constructor = UnitEntity::create;

            health = 150f;
            flying = true;
            speed = 4.5f;
            accel = 0.1f;
            drag = 0.05f;
            rotateSpeed = 10f;
            hitSize = 5;

            mineTier = 1;
            mineSpeed = 4f;
            buildSpeed = 1f;
        }};

    }
}

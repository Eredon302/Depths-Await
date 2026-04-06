package DepthsAwait.content;

import mindustry.world.blocks.environment.*;

import static DepthsAwait.content.DALiquids.*;
import static mindustry.content.StatusEffects.*;

public class DAEnvironment {

    public static Floor magmaFloor;

    public static void load(){

        magmaFloor = new Floor("magmaFloor"){{
            isLiquid = true;
            liquidDrop = magma;

            speedMultiplier = 0.6f;
            status = melting;
        }};

    }
}

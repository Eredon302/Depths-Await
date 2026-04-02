package DepthsAwait.content;

import arc.graphics.g2d.TextureRegion;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.storage.*;

import static mindustry.type.ItemStack.with;

public class DABlocks {

    //Item transport
    public static Block transferDuct;
    //Liquid transport
    //Production
    //Turrets
    //Walls
    //Special
    public static Block coreTundra;

    public static void load(){

        //Item transport

        transferDuct = new Duct("transferDuct"){

            {
                requirements(Category.distribution, with(DAItems.rhodite, 1));
                health = 90;
                speed = 3f;
                alwaysUnlocked = true;
            }

            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{
                        botRegions[0],
                        topRegions[0]
                };
            }
        };

        //Liquid transport

        //Production

        //Turrets

        //Walls

        //Special

        coreTundra = new CoreBlock("coreTundra"){{
            size = 4;
            alwaysUnlocked = true;
            isFirstTier = true;
            unitType = DAUnits.icicle;

            landDuration = 240;
            incinerateNonBuildable = true;
        }};

    }

}

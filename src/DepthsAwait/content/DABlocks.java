package DepthsAwait.content;

import DepthsAwait.world.blocks.DuctTunnel;
import arc.graphics.g2d.TextureRegion;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.storage.*;

import static mindustry.type.ItemStack.with;

public class DABlocks {

    //Item transport
    public static Block transferDuct, transferSplitway, transferOverpass, transferCrossway, transferTunnel;
    //Drills
    //Liquids
    //Production
    //Turrets
    //Walls
    //Units
    //Special
    public static Block coreTundra;

    public static void load(){

        //Item transport

        transferDuct = new Duct("transferDuct"){

            {
                requirements(Category.distribution, with(DAItems.rhodite, 1));
                health = 100;
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

        transferSplitway = new DuctRouter("transferSplitway"){{
            requirements(Category.distribution, with(DAItems.rhodite, 2));
            health = 100;
            speed = 3f;
            squareSprite = false;
            alwaysUnlocked = true;
        }};

        transferCrossway = new Junction("transferCrossway"){{
            requirements(Category.distribution, with(DAItems.rhodite, 2));
            health = 100;
            speed = 3f;
            squareSprite = false;
            alwaysUnlocked = true;
        }};

        transferOverpass = new DuctBridge("transferOverpass"){{
            requirements(Category.distribution, with(DAItems.rhodite, 2));
            health = 120;
            speed = 3f;
            buildTime = 30f;
            squareSprite = false;

            range = 4;
            itemCapacity = 10;
            hasItems = true;
            isDuct = true;
            alwaysUnlocked = true;
        }};

        /* ClankerBridgeeee */
        transferTunnel = new DuctTunnel("transferTunnel"){{
            requirements(Category.distribution, with(DAItems.rhodite, 5));
            health = 120;
            speed = 3f;
            buildTime = 30f;
            squareSprite = false;

            range = 8;
            itemCapacity = 10;
            hasItems = true;
            isDuct = true;
            alwaysUnlocked = true;
        }};

        //Drills

        //Liquids

        //Production

        //Turrets

        //Walls

        //Units

        //Special

        coreTundra = new CoreBlock("coreTundra"){{
            health = 5000;
            armor = 3;
            size = 4;
            buildTime = 1500;
            squareSprite = false;
            alwaysUnlocked = true;
            isFirstTier = true;
            unitType = DAUnits.icicle;

            landDuration = 240;
            itemCapacity = 3500;
            incinerateNonBuildable = true;
        }};

    }

}

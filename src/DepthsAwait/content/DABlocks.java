package DepthsAwait.content;

import DepthsAwait.world.blocks.*;
import arc.graphics.g2d.TextureRegion;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.storage.*;

import static mindustry.type.ItemStack.with;

public class DABlocks {

    //Item transport
    public static Block transferDuct, transferSplitway, transferOverpass, transferCrossway, transferTunnel;
    //Drills
    //Liquids
    public static Block liquidChannel, magmaTap;
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

                junctionReplacement = transferCrossway;
                bridgeReplacement = transferOverpass;
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
            alwaysUnlocked = true;
        }};

        transferCrossway = new Junction("transferCrossway"){{
            requirements(Category.distribution, with(DAItems.rhodite, 2));
            health = 100;
            speed = 3f;
            alwaysUnlocked = true;
        }};

        transferOverpass = new DuctBridge("transferOverpass"){{
            requirements(Category.distribution, with(DAItems.rhodite, 2));
            health = 120;
            speed = 3f;
            buildTime = 30f;

            range = 4;
            itemCapacity = 10;
            hasItems = true;
            isDuct = true;
            alwaysUnlocked = true;
        }};

        transferTunnel = new DuctTunnel("transferTunnel"){{
            requirements(Category.distribution, with(DAItems.rhodite, 5));
            health = 120;
            speed = 3f;
            buildTime = 30f;

            range = 15;
            itemCapacity = 10;
            hasItems = true;
            isDuct = true;
            alwaysUnlocked = true;
        }};

        //Drills

        //Liquids

        liquidChannel = new Conduit("liquidChannel"){
            {
                requirements(Category.liquid, with(DAItems.thaumium, 1));
                health = 200;

                underBullets = true;
            }

            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{
                        botRegions[0],
                        topRegions[0]
                };
            }

        };

        magmaTap = new MagmaTap("magmaTap"){{
            requirements(Category.liquid, with(DAItems.thaumium, 1));
        }};

        // TODO make the magmaTap work as well as the liquid, channel rendering and the magmaTile

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

package DepthsAwait.content;

import arc.graphics.g2d.TextureRegion;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;

import static mindustry.type.ItemStack.with;

public class DABlocks {

    //Item Transport
    public static Block transferDuct;

    public static void load(){

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

        //other objects after this

    }

}

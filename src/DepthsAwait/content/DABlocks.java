package DepthsAwait.content;

import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;

import static mindustry.type.ItemStack.with;

public class DABlocks {

    //Item Transport
    public static Block transferDuct;

    public static void load(){
        transferDuct = new Duct("transferDuct"){{
            requirements(Category.distribution, with(DAItems.rhodite, 2));
            health = 90;
            speed = 4f;
        }};
    }

}

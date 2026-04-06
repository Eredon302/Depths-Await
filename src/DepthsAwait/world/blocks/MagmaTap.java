package DepthsAwait.world.blocks;

import DepthsAwait.content.DAEnvironment;
import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.production.WallCrafter;

public class MagmaTap extends WallCrafter {

    public TextureRegion pipeRegion;

    public MagmaTap(String name) {
        super(name);
    }

    @Override
    public void load(){
        super.load();
        pipeRegion = Core.atlas.find(name + "-pipe");
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){

        Tile front = tile.nearby(Geometry.d4x[rotation], Geometry.d4y[rotation]);

        if(front != null && front.block() == DAEnvironment.magmaFloor){
            return true;
        }
        return false;
    }

    public class MagmaTapBuild extends WallCrafterBuild{

    }
}

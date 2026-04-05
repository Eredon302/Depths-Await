package DepthsAwait.world.blocks;

import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.util.Nullable;
import mindustry.game.Team;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.DirectionBridge;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.environment.StaticWall;

public class DuctTunnel extends DuctBridge {

    public TextureRegion errorRegion;

    public DuctTunnel(String name) {
        super(name);
    }

    @Override
    public boolean canPlaceOn (Tile tile, Team team, int rotation){
        for (int i = 0; i<2; i++){
            Point2 direction = Geometry.d4(rotation + i * 2);

            Tile offset = tile.nearby(direction.x, direction.y);

            if (offset != null && offset.block() instanceof StaticWall){
                return true;
            }
        }
        return false;
    }

    public class DuctTunnelBuild extends DuctBridgeBuild {

        @Nullable
        public DirectionBridgeBuild findLink(){
            for(int i = 1; i <= range; i++){
                Tile other = tile.nearby(Geometry.d4x(rotation) * i, Geometry.d4y(rotation) * i);
                if(other != null && other.build instanceof DirectionBridgeBuild build && build.block == this.block && build.team == team){
                    if(build.rotation == this.rotation){
                        return build;
                    }
                }
                if(!(other.block() instanceof StaticWall)){
                    return null;
                }
            }
            return null;
        }
    }

}
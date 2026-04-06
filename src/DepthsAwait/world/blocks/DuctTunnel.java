package DepthsAwait.world.blocks;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.util.Nullable;
import mindustry.game.Team;
import mindustry.graphics.Layer;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.environment.StaticWall;

public class DuctTunnel extends DuctBridge {

    public TextureRegion errorRegion;

    public DuctTunnel(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();
        errorRegion = Core.atlas.find(name + "-dir-error");
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        for(int i = 0; i < 2; i++){
            Point2 direction = Geometry.d4(rotation + i * 2);
            Tile offset = tile.nearby(direction.x, direction.y);

            if(offset != null && offset.block() instanceof StaticWall){
                return true;
            }
        }
        return false;
    }

    public class DuctTunnelBuild extends DuctBridgeBuild {

        @Nullable
        @Override
        public DirectionBridgeBuild findLink(){
            for(int i = 1; i <= range; i++){

                Tile other = tile.nearby(
                        Geometry.d4x(rotation) * i,
                        Geometry.d4y(rotation) * i
                );

                if(other == null) return null;

                if(other.build instanceof DirectionBridgeBuild build &&
                        build.block == this.block &&
                        build.team == team &&
                        build.rotation == this.rotation){
                    return build;
                }

                if(!(other.block() instanceof StaticWall)){
                    return null;
                }
            }

            return null;
        }

        // fast incoming check (only checks behind the bridge)
        public boolean hasIncoming(){

            int backDir = (rotation + 2) % 4;

            for(int i = 1; i <= range; i++){

                Tile other = tile.nearby(
                        Geometry.d4x(backDir) * i,
                        Geometry.d4y(backDir) * i
                );

                if(other == null) break;

                if(other.build instanceof DuctTunnelBuild otherBuild){
                    if(otherBuild.findLink() == this){
                        return true;
                    }
                }

                if(!(other.block() instanceof StaticWall)){
                    break;
                }
            }

            return false;
        }

        @Override
        public void draw(){
            super.draw();

            boolean outgoing = findLink() != null;
            boolean incoming = hasIncoming();

            if(!outgoing && !incoming){
                Draw.z(Layer.blockOver);
                Draw.rect(errorRegion, x, y, rotdeg());
            }
        }
    }
}
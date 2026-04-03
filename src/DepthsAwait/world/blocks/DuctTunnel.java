package DepthsAwait.world.blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.util.Eachable;

import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DirectionBridge;

import static mindustry.Vars.world;

public class DuctTunnel extends DuctBridge {

    public DuctTunnel(String name){
        super(name);
        buildType = DuctTunnelBuild::new;
    }

    // load base sprite safely
    @Override
    public void load(){
        super.load();
        region = Core.atlas.find(name);
    }

    // UI icon
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region};
    }

    // placement preview
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy(), plan.rotation * 90f);
    }

    // must face wall
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        if(!super.canPlaceOn(tile, team, rotation)) return false;

        int dx = 0, dy = 0;

        switch(rotation){
            case 0: dx = 1; break;
            case 1: dy = 1; break;
            case 2: dx = -1; break;
            case 3: dy = -1; break;
        }

        Tile front = world.tile(tile.x + dx, tile.y + dy);
        Tile back  = world.tile(tile.x - dx, tile.y - dy);

        return (front != null && front.block().isStatic()) ||
                (back  != null && back.block().isStatic());
    }

    public class DuctTunnelBuild extends DuctBridgeBuild {

        @Override
        public DirectionBridgeBuild findLink(){
            DirectionBridgeBuild other = super.findLink();
            if(!(other instanceof DuctTunnelBuild)) return null;

            DuctTunnelBuild ob = (DuctTunnelBuild)other;

            // prevent bridges facing each other
            if((rotation + 2) % 4 == ob.rotation){
                return null;
            }

            // verify all tiles between are walls
            int x1 = tile.x;
            int y1 = tile.y;
            int x2 = ob.tile.x;
            int y2 = ob.tile.y;

            int dx = Integer.signum(x2 - x1);
            int dy = Integer.signum(y2 - y1);

            int cx = x1 + dx;
            int cy = y1 + dy;

            boolean wallFound = false;

            while(cx != x2 || cy != y2){
                Tile t = world.tile(cx, cy);

                if(t == null || !t.block().isStatic()){
                    return null;
                }

                wallFound = true;

                cx += dx;
                cy += dy;
            }

            return wallFound ? ob : null;
        }

        @Override
        public void draw(){

            // draw base block
            Draw.rect(region, tile.drawx(), tile.drawy(), rotdeg());

            // draw bridge beam only if sprite exists
            if(findLink() != null && bridgeRegion != Core.atlas.find("error")){
                super.draw();
            }

            // error overlay detection
            boolean hasPotentialTarget = false;

            for(int i = -range; i <= range; i++){
                if(i == 0) continue;

                Tile t = world.tile(tile.x + i, tile.y);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    hasPotentialTarget = true;
                    break;
                }

                t = world.tile(tile.x, tile.y + i);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    hasPotentialTarget = true;
                    break;
                }
            }

            if(hasPotentialTarget && findLink() == null){
                Draw.color(Color.red);
                Draw.alpha(0.5f);
                Draw.rect("error", tile.drawx(), tile.drawy(), 10f, 10f);
                Draw.reset();
            }
        }
    }
}
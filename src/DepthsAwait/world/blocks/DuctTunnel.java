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

    TextureRegion dirRegion, arrowRegion;

    public DuctTunnel(String name){
        super(name);
        buildType = DuctTunnelBuild::new;
    }

    @Override
    public void load(){
        super.load();

        region = Core.atlas.find(name);
        dirRegion = Core.atlas.find(name + "-dir", region);
        arrowRegion = Core.atlas.find(name + "-arrow");
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, dirRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy(), plan.rotation * 90f);
        Draw.rect(dirRegion, plan.drawx(), plan.drawy(), plan.rotation * 90f);
    }

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

            int dx = 0, dy = 0;

            switch(rotation){
                case 0: dx = 1; break;
                case 1: dy = 1; break;
                case 2: dx = -1; break;
                case 3: dy = -1; break;
            }

            for(int i = 1; i <= range; i++){

                Tile t = world.tile(tile.x + dx * i, tile.y + dy * i);
                if(t == null) break;

                if(t.build instanceof DuctTunnelBuild){
                    DuctTunnelBuild other = (DuctTunnelBuild)t.build;

                    if((rotation + 2) % 4 == other.rotation) return null;
                    if(other.findLinkSimple() != null) return null;

                    for(int j = 1; j < i; j++){
                        Tile mid = world.tile(tile.x + dx * j, tile.y + dy * j);
                        if(mid == null || !mid.block().isStatic()) return null;
                    }

                    return other;
                }

                if(!t.block().isStatic()) return null;
            }

            return null;
        }

        public DuctTunnelBuild findLinkSimple(){

            int dx = 0, dy = 0;

            switch(rotation){
                case 0: dx = 1; break;
                case 1: dy = 1; break;
                case 2: dx = -1; break;
                case 3: dy = -1; break;
            }

            for(int i = 1; i <= range; i++){

                Tile t = world.tile(tile.x + dx * i, tile.y + dy * i);
                if(t == null) break;

                if(t.build instanceof DuctTunnelBuild){
                    return (DuctTunnelBuild)t.build;
                }

                if(!t.block().isStatic()) return null;
            }

            return null;
        }

        boolean hasIncoming(){

            for(int i = -range; i <= range; i++){
                if(i == 0) continue;

                Tile t = world.tile(tile.x + i, tile.y);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    if(((DuctTunnelBuild)t.build).findLink() == this){
                        return true;
                    }
                }

                t = world.tile(tile.x, tile.y + i);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    if(((DuctTunnelBuild)t.build).findLink() == this){
                        return true;
                    }
                }
            }

            return false;
        }

        @Override
        public void draw(){

            Draw.rect(region, tile.drawx(), tile.drawy(), rotdeg());
            Draw.rect(dirRegion, tile.drawx(), tile.drawy(), rotdeg());

            DuctTunnelBuild link = (DuctTunnelBuild)findLink();

            if(link != null){

                int dx = Integer.signum(link.tile.x - tile.x);
                int dy = Integer.signum(link.tile.y - tile.y);

                int length = Math.max(Math.abs(link.tile.x - tile.x), Math.abs(link.tile.y - tile.y));

                for(int i = 1; i < length; i++){

                    float drawx = (tile.x + dx * i) * 8f;
                    float drawy = (tile.y + dy * i) * 8f;

                    if(bridgeRegion != Core.atlas.find("error")){
                        Draw.rect(bridgeRegion, drawx, drawy, rotdeg());
                    }

                    if(arrowRegion != Core.atlas.find("error")){
                        Draw.rect(arrowRegion, drawx, drawy, rotdeg());
                    }
                }

                return;
            }

            boolean connected = hasIncoming();

            boolean potential = false;

            for(int i = -range; i <= range; i++){
                if(i == 0) continue;

                Tile t = world.tile(tile.x + i, tile.y);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    potential = true;
                    break;
                }

                t = world.tile(tile.x, tile.y + i);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    potential = true;
                    break;
                }
            }

            if(potential && !connected){
                Draw.color(Color.red);
                Draw.alpha(0.5f);
                Draw.rect("error", tile.drawx(), tile.drawy(), 10f, 10f);
                Draw.reset();
            }
        }
    }
}
package DepthsAwait.world.blocks;

import arc.graphics.g2d.Draw;
import arc.graphics.Color;
import mindustry.world.blocks.distribution.DuctBridge;
import mindustry.world.blocks.distribution.DirectionBridge;
import mindustry.world.Tile;
import mindustry.game.Team;

import static mindustry.Vars.world;

public class DuctTunnel extends DuctBridge {

    public DuctTunnel(String name){
        super(name);
        buildType = DuctTunnelBuild::new;
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        if(!super.canPlaceOn(tile, team, rotation)) return false;

        // must be orthogonally adjacent to at least one terrain wall
        for(int dx = -1; dx <= 1; dx++){
            for(int dy = -1; dy <= 1; dy++){
                if((dx == 0 && dy == 0) || (dx != 0 && dy != 0)) continue; // skip center & diagonals

                Tile other = world.tile(tile.x + dx, tile.y + dy);
                if(other != null && other.block().isStatic()){
                    return true;
                }
            }
        }
        return false;
    }

    public class DuctTunnelBuild extends DuctBridgeBuild {

        @Override
        public DirectionBridge.DirectionBridgeBuild findLink(){
            DirectionBridge.DirectionBridgeBuild other = super.findLink();
            if(other == null) return null;

            int x1 = tile.x;
            int y1 = tile.y;
            int x2 = other.tile.x;
            int y2 = other.tile.y;

            int dx = Integer.signum(x2 - x1);
            int dy = Integer.signum(y2 - y1);

            int cx = x1 + dx;
            int cy = y1 + dy;

            boolean wallFound = false;

            while(cx != x2 || cy != y2){
                Tile t = world.tile(cx, cy);

                if(t == null || !t.block().isStatic()){
                    return null; // path invalid
                } else {
                    wallFound = true; // at least one terrain wall
                }

                cx += dx;
                cy += dy;
            }

            return wallFound ? other : null;
        }

        @Override
        public void draw(){
            super.draw();

            // Check if this bridge is connected in any direction
            boolean hasOutgoing = findLink() != null;
            boolean hasIncoming = false;

            for(int dx = -range; dx <= range; dx++){
                if(dx == 0) continue;
                Tile t = world.tile(tile.x + dx, tile.y);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    DuctTunnelBuild other = (DuctTunnelBuild)t.build;
                    if(other.findLink() == this){
                        hasIncoming = true;
                        break;
                    }
                }
            }
            if(!hasIncoming){
                for(int dy = -range; dy <= range; dy++){
                    if(dy == 0) continue;
                    Tile t = world.tile(tile.x, tile.y + dy);
                    if(t != null && t.build instanceof DuctTunnelBuild){
                        DuctTunnelBuild other = (DuctTunnelBuild)t.build;
                        if(other.findLink() == this){
                            hasIncoming = true;
                            break;
                        }
                    }
                }
            }

            // Skip drawing error if already connected
            if(hasOutgoing || hasIncoming) return;

            // Check if there is any reachable but unconnectable bridge
            boolean hasPotentialTarget = false;
            for(int dx = -range; dx <= range; dx++){
                if(dx == 0) continue;
                Tile t = world.tile(tile.x + dx, tile.y);
                if(t != null && t.build instanceof DuctTunnelBuild){
                    hasPotentialTarget = true;
                    break;
                }
            }
            if(!hasPotentialTarget){
                for(int dy = -range; dy <= range; dy++){
                    if(dy == 0) continue;
                    Tile t = world.tile(tile.x, tile.y + dy);
                    if(t != null && t.build instanceof DuctTunnelBuild){
                        hasPotentialTarget = true;
                        break;
                    }
                }
            }

            // Only draw error if there is a potential target
            if(hasPotentialTarget){
                Draw.color(Color.red);
                Draw.alpha(0.5f);
                Draw.rect("error", tile.drawx(), tile.drawy(), 12f, 12f);
                Draw.reset();
            }
        }
    }
}
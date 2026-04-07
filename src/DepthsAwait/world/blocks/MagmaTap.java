package DepthsAwait.world.blocks;

import DepthsAwait.content.DAEnvironment;
import DepthsAwait.content.DAItems;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.util.Eachable;
import arc.util.Time;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.world.Tile;
import mindustry.world.blocks.production.GenericCrafter;

public class MagmaTap extends GenericCrafter {

    public TextureRegion magmaRegion, bottomRegion, pipeRegion;

    public MagmaTap(String name) {
        super(name);
        rotate = true;

        outputItem = new ItemStack(DAItems.basalticResidue, 1);
        craftTime = 120f;
    }

    @Override
    public void load(){
        super.load();
        magmaRegion = DAEnvironment.magmaFloor.region;
        bottomRegion = Core.atlas.find(name + "-bottom");
        pipeRegion   = Core.atlas.find(name + "-pipe");
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        Tile front = tile.nearby(Geometry.d4x[rotation], Geometry.d4y[rotation]);
        return front != null && front.floor() == DAEnvironment.magmaFloor;
    }

    // placement preview rendering
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){

        float worldx = x * 8f;
        float worldy = y * 8f;

        float pulse = Mathf.absin(Time.time, 6f, 1f);
        float strength = Mathf.lerp(0.25f, 0.65f, pulse);

        if(valid){
            Draw.mixcol(Color.white, strength);
        }else{
            Draw.mixcol(Pal.remove, strength);
        }

        Draw.rect(bottomRegion, worldx, worldy);
        Draw.rect(region, worldx, worldy);

        int dx = Geometry.d4x[rotation];
        int dy = Geometry.d4y[rotation];

        Draw.rect(pipeRegion, worldx + dx * 8f, worldy + dy * 8f, rotation * 90f);

        Draw.mixcol(); // reset
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        float worldx = plan.drawx();
        float worldy = plan.drawy();
        int rotation = plan.rotation;

        Draw.rect(bottomRegion, worldx, worldy);
        Draw.rect(region, worldx, worldy);

        int dx = Geometry.d4x[rotation];
        int dy = Geometry.d4y[rotation];

        Draw.rect(pipeRegion, worldx + dx * 8f, worldy + dy * 8f, rotation * 90f);
    }

    public class MagmaTapBuild extends GenericCrafterBuild{

        @Override
        public void draw() {
            MagmaTap block = (MagmaTap)this.block;

            // Draw bottom
            Draw.rect(block.bottomRegion, x, y);

            // Draw magma liquid
            float pulse = Mathf.absin(Time.time, 6f, 0.1f);
            Draw.color(Color.orange, Color.red, 0.8f + pulse);
            Draw.rect(block.magmaRegion, x, y, 6f, 6f);
            Draw.color(); // reset

            // Draw main sprite
            Draw.rect(block.region, x, y);

            // Draw pipe
            int dx = Geometry.d4x[rotation];
            int dy = Geometry.d4y[rotation];
            Draw.rect(block.pipeRegion, x + dx * 8f, y + dy * 8f, rotation * 90f);
        }
    }
}
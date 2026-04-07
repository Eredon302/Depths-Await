package DepthsAwait;

import DepthsAwait.content.*;

import arc.*;
import arc.util.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class DepthsAwait extends Mod {

    /*
    public DepthsAwait(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event or something
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'denevis' in its config)
                dialog.cont.image(Core.atlas.find("denevis-frog")).pad(20f).row();
                dialog.cont.button("Get out mane", dialog::hide).size(200f, 50f);
                dialog.show();
            });
        });
    }
    */

    @Override
    public void loadContent(){
        DAUnits.load();
        DAEnvironment.load();
        DAPlanets.load();
        DAItems.load();
        DALiquids.load();
        DABlocks.load();
    }
}

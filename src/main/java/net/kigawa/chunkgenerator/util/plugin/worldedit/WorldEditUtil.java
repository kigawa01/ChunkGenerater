package net.kigawa.chunkgenerator.util.plugin.worldedit;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.SessionManager;
import com.sk89q.worldedit.world.World;
import org.bukkit.entity.Player;

public class WorldEditUtil {
    public static Region getRegion(Player player){
        //get player
        com.sk89q.worldedit.entity.Player actor= BukkitAdapter.adapt(player);
        //get session manager
        SessionManager manager= WorldEdit.getInstance().getSessionManager();
        //get local session
        LocalSession localSession=manager.get(actor);
        //get world
        World world=localSession.getSelectionWorld();
        //create region
        Region region=null;
        //check null
        if (world!=null){
            try {
                region=localSession.getSelection(world);
            } catch (IncompleteRegionException e) {
                e.printStackTrace();
            }
        }else player.sendMessage("world is null");
        return region;
    }
}

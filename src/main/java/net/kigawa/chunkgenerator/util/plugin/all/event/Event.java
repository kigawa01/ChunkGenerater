package net.kigawa.chunkgenerator.util.plugin.all.event;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import org.bukkit.event.Listener;

public class Event implements Listener {
    KigawaPlugin plugin;
    public Event(KigawaPlugin kigawaPlugin){
        plugin=kigawaPlugin;
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
}

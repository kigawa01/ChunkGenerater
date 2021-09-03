package net.kigawa.chunkgenerator.generator;

import net.kigawa.chunkgenerator.generator.command.GenerateCommand;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;

public class Generator {
    KigawaPlugin plugin;
    public Generator(KigawaPlugin plugin){
        plugin.addCommand(new GenerateCommand(plugin,this));
        this.plugin=plugin;
    }

    public void generate(String world,int x,int y){
        plugin.getServer().getWorld(world).regenerateChunk(x,y);
    }
}

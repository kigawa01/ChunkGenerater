package net.kigawa.chunkgenerator;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChunkGenerator extends KigawaPlugin {

    @Override
    public void addConfigDefault(FileConfiguration config) {

    }

    @Override
    public void onStart() {
        Generator generator=new Generator(this);
    }
}

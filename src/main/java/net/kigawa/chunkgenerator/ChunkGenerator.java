package net.kigawa.chunkgenerator;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.worldedit.world.BlockRegion;
import org.bukkit.configuration.file.FileConfiguration;

public final class ChunkGenerator extends KigawaPlugin {

    @Override
    public void addConfigDefault(FileConfiguration config) {

    }

    @Override
    public void onStart() {
        Generator generator = new Generator(this);
    }
}

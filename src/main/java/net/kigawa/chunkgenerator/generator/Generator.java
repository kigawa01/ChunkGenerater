package net.kigawa.chunkgenerator.generator;

import net.kigawa.chunkgenerator.generator.command.GenerateCommand;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;

public class Generator {
    KigawaPlugin plugin;

    public Generator(KigawaPlugin plugin) {
        plugin.addCommand(new GenerateCommand(plugin, this));
        this.plugin = plugin;
    }

    public void generate(String world, int x, int z) {
        World world1 = plugin.getServer().getWorld("world");
        ChunkGenerator.ChunkData chunkData = plugin.getServer().createChunkData(world1);
        Chunk chunk = world1.getChunkAt(x, z);
        for (int bX = 1; bX < 16; bX++) {
            for (int bY = 1; bY < 256; bY++) {
                for (int bZ = 1; bZ < 16; bZ++) {
                    Block block = chunk.getBlock(bX, bY, bZ);
                    BlockData data= chunkData.getBlockData(bX,bY,bZ);
                    block.setBlockData(data);
                }
            }
        }
        //ChunkGenerator chunkGenerator=new ChunkGenerator(){};
        //chunkGenerator.generateChunkData(world1, new Random(), x, z, new CustomBiomeGrid(world1));
    }
}

class CustomBiomeGrid implements ChunkGenerator.BiomeGrid {
    private World world;

    public CustomBiomeGrid(World world) {
        this.world = world;
    }

    public Biome getBiome(int x, int z) {
        return world.getBiome(x, z);
    }

    public void setBiome(int x, int z, Biome bio) {
        world.setBiome(x, z, bio);
    }

    public Biome getBiome(int x, int y, int z) {
        return world.getBiome(x, y, z);
    }

    public void setBiome(int x, int y, int z, Biome bio) {
        world.setBiome(x, y, z, bio);
    }
}

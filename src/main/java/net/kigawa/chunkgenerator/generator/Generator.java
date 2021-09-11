package net.kigawa.chunkgenerator.generator;

import net.kigawa.chunkgenerator.generator.command.GenerateCommand;
import net.kigawa.chunkgenerator.generator.command.PlayerData;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.message.sender.InfoSender;
import net.kigawa.chunkgenerator.util.plugin.worldedit.world.BlockRegion;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Generator implements Listener {
    private static World resource;
    private boolean timer;
    private KigawaPlugin plugin;
    private Random random = new Random();
    private List<GeneratorData> dataList;
    private List<PlayerData> playerDataList;

    public Generator(KigawaPlugin plugin) {
        plugin.addCommand(new GenerateCommand(plugin, this));
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        timer = plugin.getConfig().getBoolean("timer");
        dataList = plugin.getRecorder().loadAll(GeneratorData.class, "generator");
        playerDataList = plugin.getRecorder().loadAll(PlayerData.class, "playerData");

        //get world
        if (resource == null) {
            WorldCreator creator = new WorldCreator("ChunkGenerator");
            resource = creator.createWorld();
        }

        //run timer
        generateTimer();
    }

    public void generate(String world, int x, int z) {
        //get chunk
        World original = plugin.getServer().getWorld(world);
        Chunk originChunk = original.getChunkAt(x, z);
        Chunk resourceChunk = resource.getChunkAt(random.nextInt(10000000), random.nextInt(10000000));

        //teleport player
        List<Player> players = original.getPlayers();
        for (Player player : players) {
            if (player.getWorld().equals(original) && player.getLocation().getChunk().equals(originChunk)) {
                player.teleport(original.getSpawnLocation());
                new InfoSender("再生成のためテレポートしました", player);

            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                teleportOfflinePlayers(plugin.getServer().getOfflinePlayers(), original.toString(), originChunk);
            }
        }.runTaskAsynchronously(plugin);

        //set chunk block
        for (int bX = 0; bX < 16; bX++) {
            for (int bY = 0; bY < 256; bY++) {
                for (int bZ = 0; bZ < 16; bZ++) {
                    Block block = originChunk.getBlock(bX, bY, bZ);
                    block.setBlockData(resourceChunk.getBlock(bX, bY, bZ).getBlockData());
                }
            }
        }

        //reset
        resourceChunk.unload();
    }

    public void teleportOfflinePlayers(OfflinePlayer[] offlinePlayers, String world, Chunk chunk) {
        for (OfflinePlayer player : offlinePlayers) {
            PlayerData data = plugin.getRecorder().load(PlayerData.class, "playerData", player.getUniqueId().toString());
            if (data != null && data.getWorld().equals(world) && data.getX() == chunk.getX() && data.getZ() == chunk.getZ()) {
                data.setNeedTp(true);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Chunk chunk = player.getLocation().getChunk();
        PlayerData data = new PlayerData();
        data.setName(player.getUniqueId().toString());
        data.setX(chunk.getX());
        data.setZ(chunk.getZ());
        data.setWorld(player.getWorld().getName());
        plugin.getRecorder().save(data, "playerData");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerData data = plugin.getRecorder().load(PlayerData.class, "playerData", player.getUniqueId().toString());
        if (data != null && data.getNeedTp()) {
            player.teleport(player.getWorld().getSpawnLocation());
            data.setNeedTp(false);
        }
    }

    public void generateTimer() {
        if (timer) {
            Calendar calendar = Calendar.getInstance();
            if (calendar.get(Calendar.MINUTE) == 0) {
                for (GeneratorData data : dataList) {
                    if (random.nextInt(101) <= plugin.getConfig().getInt("percent")) {
                        new RegionGenerator(data, this, plugin);
                    }
                }
            }
            Bukkit.getScheduler().runTaskLater(plugin, this::generateTimer, 20 * 60);
        }
    }

    public String generateRegion(String name) {
        GeneratorData data = getGeneratorData(name);
        if (data == null) {
            return name + " is not exit";
        }
        new RegionGenerator(data, this, plugin);
        return "generate " + name;
    }

    public String setGenerateRegion(String name, BlockRegion region) {
        GeneratorData data = getGeneratorData(name);
        Chunk chunk = region.getSLocation(plugin).getChunk();
        Chunk chunk1 = region.getELocation(plugin).getChunk();
        if (data == null) {
            data = new GeneratorData();
            data.setName(name);
            dataList.add(data);
        }
        data.setWorld(region.getWorld());
        data.setsX(chunk.getX());
        data.setsZ(chunk.getZ());
        data.setmX(chunk1.getX());
        data.setmZ(chunk1.getZ());
        save(data);
        return "set generate region";
    }

    public String removeGenerateRegion(String name) {
        GeneratorData data = getGeneratorData(name);
        if (data == null) {
            return name + " is not exit";
        }
        dataList.remove(data);
        remove(data);
        return "remove " + name;
    }

    public void save(GeneratorData data) {
        plugin.getRecorder().save(data, "generator");
    }

    public void remove(GeneratorData data) {
        plugin.getRecorder().remove(data, "generator");
    }

    public GeneratorData getGeneratorData(String name) {
        for (GeneratorData data : dataList) {
            if (data.getName().equals(name)) {
                return data;
            }
        }
        return null;
    }
}

class RegionGenerator {
    private Generator generator;
    private GeneratorData data;
    private int x;
    private int z;
    private JavaPlugin plugin;

    public RegionGenerator(GeneratorData data, Generator generator, JavaPlugin plugin) {
        this.data = data;
        this.generator = generator;
        this.plugin = plugin;
        x = data.getsX();
        z = data.getsZ();
        generate();
    }

    public void generate() {
        if (x <= data.getmX()) {
            if (z <= data.getmZ()) {

                generator.generate(data.world, x, z);

                z++;
                Bukkit.getScheduler().runTaskLater(plugin, this::generate, 1);
                return;
            }
            z = data.getsZ();
            x++;
            Bukkit.getScheduler().runTaskLater(plugin, this::generate, 1);
        }
    }
}
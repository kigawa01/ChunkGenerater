package net.kigawa.chunkgenerator.util.plugin.gate;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.all.Util;
import net.kigawa.chunkgenerator.util.plugin.all.world.PlayerRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Gate {
    private PlayerRegion region;
    private GateData data;
    private KigawaPlugin plugin;
    private List<String> players;

    public Gate(KigawaPlugin plugin, GateData data, PlayerRegion region) {
        this.plugin = plugin;
        this.data = data;
        this.region = region;
        players = new ArrayList<>();

        plugin.logger("Gate " + region.getWidth());

        save();

    }

    public Gate(KigawaPlugin plugin, GateData data) {
        this.plugin = plugin;
        this.data = data;
        this.region = new PlayerRegion(data.getWorld(), data.getCenter()[0], data.getCenter()[1], data.getCenter()[2], data.getSize()[0], data.getSize()[1], data.getSize()[2]);
        players = new ArrayList<>();

        save();
    }

    public boolean contain(Player player) {
        return region.contain(player);
    }

    public boolean contain(String name) {
        return data.getName().equals(name);
    }

    public boolean containAllowed(String playerName) {
        return players.contains(playerName);
    }

    public String teleport(Player player) {
        if (players.contains(player.getName())) {
            player.teleport(new Location(plugin.getServer().getWorld(data.getWorld()), region.getcX(), region.getsY(), region.getcZ()));
            return "teleport " + getName();
        } else return "you can't teleport " + getName();
    }

    public String resetAllowed() {
        players.clear();
        save();
        return "clear allowed";
    }

    public void addAllowed(String playerName) {
        if (!players.contains(playerName)) {
            players.add(playerName);
            save();
        }
    }

    public void save() {
        if (data.getLinked() == null) {
            data.setLinked(new String[0]);
        }
        data.setRegion(region);
        plugin.getRecorder().save(data, "gate");
    }

    public String setLinked(String linked) {
        List<String> linkedGates = Util.getList(data.getLinked());
        linkedGates.add(linked);
        data.setLinked(Util.getStringArrangement(linkedGates));
        return "linked gate is set";
    }

    public String getName() {
        return data.getName();
    }

    public String[] getLinked() {
        return data.getLinked();
    }
}

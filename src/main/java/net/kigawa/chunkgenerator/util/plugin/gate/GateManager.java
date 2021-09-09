package net.kigawa.chunkgenerator.util.plugin.gate;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.message.sender.InfoSender;
import net.kigawa.chunkgenerator.util.plugin.worldedit.world.PlayerRegion;
import net.kigawa.chunkgenerator.util.plugin.worldedit.WorldEditUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class GateManager {
    private KigawaPlugin plugin;
    private List<Player> wait;
    private List<Gate> gates;

    public GateManager(KigawaPlugin plugin) {
        this.plugin = plugin;
        List<GateData> gateData;
        gateData = plugin.getRecorder().loadAll(GateData.class, "gate");
        gates = new ArrayList<>();
        for (GateData data : gateData) {
            gates.add(new Gate(plugin, data));
        }
        wait=new ArrayList<>();
    }

    public void moveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        boolean contain=false;
        for (Gate gate : gates) {
            if (gate.contain(player)) {
                addAllowed(gate,player);
                contain=true;
                if (!wait.contains(player)) {
                    sendGates(player);
                    wait.add(player);
                    return;
                }
            }
        }
        if (!contain){
            wait.remove(player);
        }
    }

    public void addAllowed(Gate gate,Player player){
        gate.addAllowed(player.getName());
        for (String linked:gate.getLinked()){
            Gate gate1=getGate(linked);
            if (gate1!=null){
                addAllowed(gate1,player);
            }
        }
    }

    public String sendGates(Player player) {
        new InfoSender("テレポート可能なゲート ", player);

        for (Gate gate : gates) {
            if (gate.containAllowed(player.getName())) {
                TextComponent textComponent = new TextComponent(gate.getName()+("   (Click)"));
                textComponent.setColor(ChatColor.BLUE);
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gate tp " + gate.getName()));
                player.spigot().sendMessage(textComponent);
            }
        }

        return "";
    }

    public String teleport(String toGate, Player player) {
        Gate gate = getGate(toGate);
        if (gate != null) {
            return gate.teleport(player);
        }
        return toGate + " is null";
    }

    public String resetAllowed() {
        for (Gate gate : gates) {
            gate.resetAllowed();
        }
        return "reset allowed";
    }

    public String create(String gateName, Player player) {
        if (!contain(gateName)) {
            GateData data = new GateData();
            data.setName(gateName);
            gates.add(new Gate(plugin, data, new PlayerRegion(WorldEditUtil.getRegion(player))));
            return "gate is set";
        }
        return "gate is exit";
    }

    public boolean contain(String gateName) {
        if (gates == null) {
            gates = new ArrayList<>();
        }
        for (Gate gate : gates) {
            if (gate.getName().equals(gateName)) {
                return true;
            }
        }
        return false;
    }

    public String setLinked(String gateName,String linked){
        Gate gate=getGate(gateName);
        if (gate!=null){
            return gate.setLinked(linked);
        }
        return gateName+" is not exit";
    }

    public Gate getGate(String gateName) {
        for (Gate gate : gates) {
            if (gate.contain(gateName)) {
                return gate;
            }
        }
        return null;
    }
}
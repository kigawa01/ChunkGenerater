package net.kigawa.chunkgenerator.util.plugin.gate.command.gate;

import net.kigawa.chunkgenerator.util.plugin.gate.GateManager;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.PluginUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GateTeleport extends GateCommandBase{
    public GateTeleport(KigawaPlugin kigawaPlugin, GateManager gate) {
        super(kigawaPlugin, gate);
    }

    @Override
    public String getName() {
        return "tp";
    }

    @Override
    public boolean onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==2){
            Player player= PluginUtil.getPlayer(commandSender);
            if (player!=null) {
                commandSender.sendMessage( getManager().teleport(strings[1],player));
                return true;
            }
        }
        return false;
    }

    @Override
    public String errorMessage() {
        return "/gate tp <gate name>";
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }
}

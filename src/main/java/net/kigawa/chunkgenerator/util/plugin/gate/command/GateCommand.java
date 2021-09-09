package net.kigawa.chunkgenerator.util.plugin.gate.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.gate.command.gate.GateCreate;
import net.kigawa.chunkgenerator.util.plugin.gate.command.gate.GateList;
import net.kigawa.chunkgenerator.util.plugin.gate.command.gate.GateTeleport;
import net.kigawa.chunkgenerator.util.plugin.gate.command.gate.SetLinked;
import net.kigawa.chunkgenerator.util.plugin.gate.GateManager;
import net.kigawa.chunkgenerator.util.plugin.all.PluginUtil;
import net.kigawa.chunkgenerator.util.plugin.all.command.FirstCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GateCommand extends FirstCommand {
    private GateManager manager;
    private KigawaPlugin plugin;

    public GateCommand(KigawaPlugin plugin, GateManager manager) {
        super(plugin);
        this.plugin = plugin;
        this.manager = manager;
        addSubcommands(new GateCreate(plugin,manager));
        addSubcommands(new GateList(plugin,manager));
        addSubcommands(new GateTeleport(plugin,manager));
        addSubcommands(new SetLinked(plugin,manager));
    }

    @Override
    public String getName() {
        return "gate";
    }

    @Override
    public boolean onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            Player player = PluginUtil.getPlayer(commandSender);
            if (player != null) {
                manager.teleport(strings[0], player);
            }
        }
        return false;
    }

    @Override
    public String errorMessage() {
        return "/gate <subcommand>";
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }
}

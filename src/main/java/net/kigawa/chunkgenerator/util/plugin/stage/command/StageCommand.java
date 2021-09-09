package net.kigawa.chunkgenerator.util.plugin.stage.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.command.FirstCommand;
import net.kigawa.chunkgenerator.util.plugin.stage.StageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class StageCommand extends FirstCommand {
    KigawaPlugin plugin;


    public StageCommand(KigawaPlugin plugin, StageManager manager) {
        super(plugin);
        this.plugin=plugin;
        addSubcommands(new CreateCommand(plugin,manager));
        addSubcommands(new SetStartLoc(plugin,manager));
        addSubcommands(new SetStage(plugin,manager));
    }
    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }
}

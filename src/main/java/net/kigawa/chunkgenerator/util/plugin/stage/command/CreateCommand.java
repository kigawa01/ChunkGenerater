package net.kigawa.chunkgenerator.util.plugin.stage.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.stage.StageManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CreateCommand extends StageCommandBase {
    public CreateCommand(KigawaPlugin kigawaPlugin, StageManager manager) {
        super(kigawaPlugin,manager);
        plugin = kigawaPlugin;
    }

    KigawaPlugin plugin;

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public boolean onThisCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length == 2) {
            //set stage
            getManager().setStage(strings[1], commandSender);
        } else {
            //send error
            commandSender.sendMessage("/stage create <name>");
        }
        return true;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public String errorMessage() {
        return null;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, org.bukkit.command.Command command, String label, String[] strings) {
        return null;
    }
}

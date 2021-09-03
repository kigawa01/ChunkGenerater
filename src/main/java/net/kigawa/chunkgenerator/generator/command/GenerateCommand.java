package net.kigawa.chunkgenerator.generator.command;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.generator.command.generate.Regenerate;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.command.FirstCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GenerateCommand extends FirstCommand {
    public GenerateCommand(KigawaPlugin plugin, Generator generator) {
        super(plugin);
        addSubcommands(new Regenerate(plugin,generator));
    }

    @Override
    public String getName() {
        return "generator";
    }

    @Override
    public boolean onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public String errorMessage() {
        return "/generate";
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, Command command, String label, String[] strings) {
        return null;
    }
}

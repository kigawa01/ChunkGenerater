package net.kigawa.chunkgenerator.generator.command;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.generator.command.generate.GenerateRegion;
import net.kigawa.chunkgenerator.generator.command.generate.Regenerate;
import net.kigawa.chunkgenerator.generator.command.generate.RemoveRegion;
import net.kigawa.chunkgenerator.generator.command.generate.SetRegion;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.command.FirstCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GenerateCommand extends FirstCommand {
    public GenerateCommand(KigawaPlugin plugin, Generator generator) {
        super(plugin);
        addSubcommands(new Regenerate(plugin, generator));
        addSubcommands(new SetRegion(plugin, generator));
        addSubcommands(new RemoveRegion(plugin, generator));
        addSubcommands(new GenerateRegion(plugin, generator));
    }

    @Override
    public String getName() {
        return "generator";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }

    @Override
    public String errorMessage() {
        return "/generate";
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

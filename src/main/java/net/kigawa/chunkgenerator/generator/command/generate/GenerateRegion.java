package net.kigawa.chunkgenerator.generator.command.generate;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GenerateRegion extends GenerateCommandBase {
    public GenerateRegion(KigawaPlugin kigawaPlugin, Generator generator) {
        super(kigawaPlugin, generator);
    }

    @Override
    public String getName() {
        return "generateregion";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2) {
            getGenerator().generateRegion(strings[1]);
        }
        return null;
    }

    @Override
    public String errorMessage() {
        return "/generator generateregion <name>";
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

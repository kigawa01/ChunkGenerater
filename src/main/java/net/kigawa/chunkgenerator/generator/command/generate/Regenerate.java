package net.kigawa.chunkgenerator.generator.command.generate;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.PluginUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Regenerate extends GenerateCommandBase{
    public Regenerate(KigawaPlugin kigawaPlugin, Generator generator) {
        super(kigawaPlugin, generator);
    }

    @Override
    public String getName() {
        return "regenerate";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length==3&&commandSender instanceof Player){
            getGenerator().generate(PluginUtil.getWorld(commandSender).getName(),Integer.valueOf(strings[1]),Integer.valueOf(strings[2]));
            return  "regenerated!";
        }
        return null;
    }

    @Override
    public String errorMessage() {
        return "/generate regenerate <x> <z>";
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

package net.kigawa.chunkgenerator.generator.command.generate;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.PluginUtil;
import net.kigawa.chunkgenerator.util.plugin.worldedit.world.BlockRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetRegion extends GenerateCommandBase {
    public SetRegion(KigawaPlugin kigawaPlugin, Generator generator) {
        super(kigawaPlugin, generator);
    }

    @Override
    public String getName() {
        return "setregion";
    }

    @Override
    public String onThisCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2 && commandSender instanceof Player) {
            return getGenerator().setGenerateRegion(strings[1], new BlockRegion(PluginUtil.getPlayer(commandSender)));
        }
        return null;
    }

    @Override
    public String errorMessage() {
        return "/generator setregion <name>";
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

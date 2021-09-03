package net.kigawa.chunkgenerator.util.plugin.stage.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.stage.StageManager;
import org.bukkit.World;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetStage1 extends StageCommandBase {
    KigawaPlugin plugin;

    public SetStage1(KigawaPlugin kigawaPlugin, StageManager manager) {
        super(kigawaPlugin,manager);
        plugin = kigawaPlugin;
    }

    @Override
    public String getName() {
        return "setstage1";
    }

    @Override
    public boolean onThisCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length == 5) {
            if (commandSender instanceof Player | commandSender instanceof BlockCommandSender) {
                World world;
                if (commandSender instanceof Player) {
                    world = ((Player) commandSender).getWorld();
                } else {
                    world = ((BlockCommandSender) commandSender).getBlock().getWorld();
                }
                getManager().setStage1(strings[1], world.getName(),
                        Integer.valueOf(strings[2]), Integer.valueOf(strings[3]), Integer.valueOf(strings[4]), commandSender);
                commandSender.sendMessage("set start point of stage");
            }

        } else {
            commandSender.sendMessage("/stage setstage1 <stage name> <x> <y> <z>");
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
    public List<String> getTabStrings(CommandSender sender, org.bukkit.command.Command command, String label, String[] strings) {
        return null;
    }
}

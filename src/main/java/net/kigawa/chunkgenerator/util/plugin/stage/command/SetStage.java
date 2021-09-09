package net.kigawa.chunkgenerator.util.plugin.stage.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.PluginUtil;
import net.kigawa.chunkgenerator.util.plugin.stage.StageManager;
import net.kigawa.chunkgenerator.util.plugin.worldedit.WorldEditUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetStage extends StageCommandBase {
    public SetStage(KigawaPlugin kigawaPlugin, StageManager manager) {
        super(kigawaPlugin, manager);
    }

    @Override
    public String getName() {
        return "setstage";
    }


    @Override
    public String onThisCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length == 2) {
            Player player = PluginUtil.getPlayer(commandSender);
            if (player != null) {
                getManager().setStage(strings[1], WorldEditUtil.getRegion(player), commandSender);
                return "set stage";
            }
        }
        return null;
    }

    @Override
    public int getWordNumber() {
        return 0;
    }

    @Override
    public String errorMessage() {
        return "/onigocreate setoniwait <stage name>";
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<String> getTabStrings(CommandSender sender, org.bukkit.command.Command command, String label, String[] strings) {
        if (strings.length == 2) {
            return getManager().getStageNames();
        }
        return null;
    }
}

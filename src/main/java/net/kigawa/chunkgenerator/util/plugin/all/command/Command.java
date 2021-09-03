package net.kigawa.chunkgenerator.util.plugin.all.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class Command extends TabList {
    private KigawaPlugin plugin;
    private List<Command> subCommands;
    private List<String> permissions;


    public Command(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
        plugin = kigawaPlugin;

        subCommands = new ArrayList<>();
    }

    public abstract boolean onThisCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

    public abstract int getWordNumber();

    public abstract String errorMessage();


    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        plugin.logger(getName() + " onAlways");

        if (subCommands != null) {
            if (strings.length>getWordNumber()) {
                if (subCommands.contains(new EqualsCommand(strings[getWordNumber()]))) {
                    Command subCommand = subCommands.get(subCommands.indexOf(new EqualsCommand(strings[getWordNumber()])));
                    return subCommand.onCommand(commandSender, command, s, strings);
                }
            }
        }
        //check permission
        if (checkPermission(commandSender)) {
            plugin.logger(getName() + " onNotFoundSubcommand");

            if (!onThisCommand(commandSender, command, s, strings)) {
                commandSender.sendMessage(errorMessage());
            }
            return true;
        } else {
            commandSender.sendMessage("need permission");
        }
        return true;
    }

    public void addSubcommands(Command subCommand) {
        subCommands.add(subCommand);
        addTabLists(subCommand);
    }

    public boolean checkPermission(CommandSender sender) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(permissions.get(0));
        for (int i = 1; i < permissions.size(); i++) {
            stringBuilder.append(".").append(permissions.get(i));
        }
        if (sender.hasPermission(stringBuilder.toString()) | sender.hasPermission(stringBuilder.toString() + ".*"))
            return true;
        return false;
    }

    public void setPermission(List<String> permission) {
        List<String> permission1 = new ArrayList<>(permission);
        permission1.add(getName());
        List<Command> commands = getSubCommands();
        if (commands != null) {
            for (Command command : commands) {
                command.setPermission(permission1);
            }
        }
        this.permissions = permission1;
    }

    public KigawaPlugin getPlugin() {
        return plugin;
    }

    public List<Command> getSubCommands() {
        return subCommands;
    }


}

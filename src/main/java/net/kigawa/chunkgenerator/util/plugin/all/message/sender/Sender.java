package net.kigawa.chunkgenerator.util.plugin.all.message.sender;

import net.kigawa.chunkgenerator.util.all.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Sender {
    private List<CommandSender> senders = new ArrayList<>();

    public Sender(CommandSender sender){
        this.senders.add(sender);
    }
    public Sender(List<CommandSender> senders){
        this.senders.addAll(senders);
    }

    public void sendItem(String itemName, String description) {
        sendMessage(senders, ChatColor.BLUE + itemName + ChatColor.WHITE + ": " + description);
    }

    public void sendItem(String itemName, int[] description) {
        sendItem(itemName, Util.createString(description));
    }

    public void sendItem(String itemName, int i) {
        sendItem(itemName, Integer.toString(i));
    }

    public List<CommandSender> getSenders() {
        return senders;
    }
    public static void sendMessage(List<CommandSender> senders,String message){
        for (CommandSender sender: senders){
            sender.sendMessage(message);
        }
    }
}

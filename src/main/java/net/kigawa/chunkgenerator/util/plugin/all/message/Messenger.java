package net.kigawa.chunkgenerator.util.plugin.all.message;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Messenger {
    KigawaPlugin plugin;

    public Messenger(KigawaPlugin plugin) {
        this.plugin = plugin;
    }

    public static void sendMessage(List<Player> players, String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }

    public void sendTitle(List<Player> players, String title, String subTitle) {
        sendTitle(players, title, subTitle, 10, 20, 10);
    }

    public void sendTitle(List<Player> players, String title) {
        sendTitle(players, title, "");
    }

    public void sendTitle(List<Player> players,String title,int fadein,int stay,int fadeout){
        sendTitle(players,title,"",fadein,stay,fadeout);
    }

    public void sendTitle(List<Player> players, String title, String subTitle, int fadein, int stay, int fadeout) {
        for (Player player : players) {
            player.sendTitle(title, subTitle, fadein, stay, fadeout);
        }
    }

    public void sendTitleLater(List<Player> players, String title, Long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                sendTitle(players, title);
            }
        }.runTaskLater(plugin, delay);
    }
}

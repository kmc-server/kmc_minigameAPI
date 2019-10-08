package nl.bramkoene.minigameapi.messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class titles {
    public void sendTitleToAllPlayers(String Title, String Subtitle){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle(Title, Subtitle, 8, 100, 8);
        }
    }

    public void sendTitletoPlayers(String Title, String Subtitle, List<Player> players) {
        for (Player player : players) {
            player.sendTitle(Title, Subtitle, 8, 100, 8);
        }
    }
}

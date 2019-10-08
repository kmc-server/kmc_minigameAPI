package nl.bramkoene.knockarena.messages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class titles {
    public void sendTitleToAllPlayers(String Title, String Subtitle){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle(Title, Subtitle, 2, 5, 2);
        }
    }

    public void sendTitletoPlayers(String Title, String Subtitle, List<Player> players) {
        for (Player player : players) {
            player.sendTitle(Title, Subtitle, 2, 5, 2);
        }
    }
}

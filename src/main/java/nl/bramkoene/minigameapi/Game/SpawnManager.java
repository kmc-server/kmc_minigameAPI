package nl.bramkoene.minigameapi.Game;

import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnManager {
    public List<Location> spawnlocations = new ArrayList<Location>();
    public MiniGameAPI plugin;
    public Location lobbySpawn;


    public SpawnManager(MiniGameAPI pl){
        this.plugin = pl;
        if(plugin.getConfigManager().getCollectors().contains("GameSpawn")) {
            spawnlocations = (List<Location>) plugin.getConfigManager().getCollectors().getList("GameSpawn");
        }
        if(plugin.getConfigManager().getCollectors().contains("LobbySpawn")){
            this.lobbySpawn = plugin.getConfigManager().getCollectors().getLocation("LobbySpawn");
        }
    }

    public boolean spawnPlayer(Player player){
        if(spawnlocations.size() <= 0){
            return false;
        }
        Random rand = new Random();
        int n = rand.nextInt(spawnlocations.size());
        player.teleport(spawnlocations.get(n));
        return false;
    }
}

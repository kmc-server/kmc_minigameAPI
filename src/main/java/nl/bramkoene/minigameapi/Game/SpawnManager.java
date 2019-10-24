package nl.bramkoene.minigameapi.Game;

import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnManager {
    public List<Location> spawnLocations = new ArrayList<Location>();
    public GameConnector game;
    public Location lobbySpawn;

    public SpawnManager(GameConnector pl){
        this.game = pl;
    }

    public boolean spawnPlayer(Player player){
        if(spawnLocations.size() <= 0){
            return false;
        }
        Random rand = new Random();
        int n = rand.nextInt(spawnLocations.size());
        player.teleport(spawnLocations.get(n));
        return false;
    }
}

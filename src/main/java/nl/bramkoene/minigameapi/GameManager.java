package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Enums.GameState;
import nl.bramkoene.minigameapi.Game.SpawnManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager implements Listener {
    public MiniGameAPI plugin;
    protected Location lobbySpawn;
    public SpawnManager spawnManager;
    private int lobbyCountDown = 10;
    private int playerNeeded = 2;
    public GameState gameState = GameState.FREE;
    public List<Player> joinedPlayers = new ArrayList<>();

    public GameManager(MiniGameAPI pl){
        this.plugin = pl;
        this.spawnManager = new SpawnManager(plugin);
    }

    public void setupGame(){
        this.setLobbyCountDown(20);
        this.gameState = GameState.IN_LOBBY;
    }

    public void lobbyWait(Player player){
        joinedPlayers.add(player);

        playerCheck(joinedPlayers.size());
    }

    public void playerCheck(int online){
        if(online >= playerNeeded){
            lobbyCountDown();
        }
    }

    public void gameStart(){
        for(Player player : joinedPlayers){
            this.spawnManager.spawnPlayer(player);
        }
        this.gameState = GameState.IN_GAME;
    }

    public void gameStop(){
        this.gameState = GameState.FREE;
    }

    public void lobbyCountDown(){
        new BukkitRunnable() {
            @Override
            public void run(){
                if(lobbyCountDown > 0){
                    lobbyCountDown = lobbyCountDown -1;
                }else{
                    gameStart();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20l);
    }

    public int getLobbyCountDown() {
        return lobbyCountDown;
    }

    public void setLobbyCountDown(int lobbyCountDown) {
        this.lobbyCountDown = lobbyCountDown;
    }

    public int getPlayerNeeded() {
        return playerNeeded;
    }

    public void setPlayerNeeded(int playerNeeded) {
        this.playerNeeded = playerNeeded;
    }

}

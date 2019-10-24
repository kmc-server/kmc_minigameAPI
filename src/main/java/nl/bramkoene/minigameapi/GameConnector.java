package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Enums.GameState;
import nl.bramkoene.minigameapi.Game.SpawnManager;
import nl.bramkoene.minigameapi.teams.Team;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GameConnector {
    public String gameName = "this";
    public List<Team> joinedTeams = new ArrayList<>();

    protected Location lobbySpawn;
    public SpawnManager spawnManager;
    private int lobbyCountDown = 10;
    private int playerNeeded = 2;
    public GameState gameState = GameState.FREE;

    public GameConnector(){
        this.spawnManager = new SpawnManager(this);
    }

    public boolean onDeath(Player player){
        return false;
    }

    public void InitGame(){
        this.setLobbyCountDown(20);
        this.gameState = GameState.IN_LOBBY;
    }

    public void lobbyWait(Player player){


        playerCheck(joinedTeams.size());
    }

    public void playerCheck(int online){
        if(online >= playerNeeded){
            lobbyCountDown();
        }
    }

    public void gameStart(){
//        for(Player player : joinedPlayers){
//            this.spawnManager.spawnPlayer(player);
//        }
        this.gameState = GameState.IN_GAME;
    }

    public void gameStop(){
        this.gameState = GameState.FREE;
    }

    public void lobbyCountDown(){
//        new BukkitRunnable() {
//            @Override
//            public void run(){
//                if(lobbyCountDown > 0){
//                    lobbyCountDown = lobbyCountDown -1;
//                }else{
//                    gameStart();
//                }
//            }
//        }.runTaskTimerAsynchronously(plugin, 0, 20l);
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

    public String getGameName() {
        return gameName;
    }
}

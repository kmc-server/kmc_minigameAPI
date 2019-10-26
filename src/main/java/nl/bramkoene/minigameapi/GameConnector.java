package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Enums.GameState;
import nl.bramkoene.minigameapi.Game.SpawnManager;
import nl.bramkoene.minigameapi.GameCreation.GameConfigBean;
import nl.bramkoene.minigameapi.teams.Team;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SameReturnValue")
public class GameConnector{
    // Overridable variables
    public String gameName = "this";

    // These values are minimal values for the config
    public int minPlayers = 2;
    public int teamPlayers = 1;
    public boolean allowRespawn = true;

    private List<Team> joinedTeams = new ArrayList<>();

    protected GameConfigBean gameConfig;
    protected Location lobbySpawn;
    private SpawnManager spawnManager;
    private int lobbyCountDown = 10;
    private int playerNeeded = 2;
    private GameState gameState = GameState.FREE;

    public GameConnector(GameConfigBean game){
        this.gameConfig = game;
        this.spawnManager = new SpawnManager(this);
    }

    public void Respawn(){
    }

    public void InitGame(){
        this.setLobbyCountDown(20);
        this.gameState = GameState.IN_LOBBY;
    }

    public void lobbyWait(){


        playerCheck(joinedTeams.size());
    }

    private void playerCheck(int online){
        if(online >= playerNeeded){
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

    public int getLobbyCountDown() {
        return lobbyCountDown;
    }

    private void setLobbyCountDown(int lobbyCountDown) {
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

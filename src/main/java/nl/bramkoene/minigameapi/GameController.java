package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Enums.GameConnectorTypes;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.*;

public class
GameController implements Listener {
    /**
     * A {@link List} of {@link GameConnector}s that are currently running
     */
    private static List<GameConnector> CreatedGames = new ArrayList<>();
    /**
     * A {@link List} of {@link GameConnector}s that are currently registered
     */
    public static List<GameConnector> registeredGames = new ArrayList<>();

    /**
     * Adds a given gamemode to the registered games and registers the game for creation
     * @param gameMode A {@link GameConnector} that gets added to the regisered games
     */
    public static void RegisterGamemode(GameConnector gameMode){
        registeredGames.add(gameMode);
        Bukkit.getLogger().info("Registering new gamemode: " + gameMode.getGameName());
    }

    /**
     * Adds a instance of the {@link GameConnector} to the createdGames array
     * @param gameMode a given instance of a {@link GameConnector}
     */
    public static void CreateGame(GameConnector gameMode){
        gameMode.gameConnectorType = GameConnectorTypes.CREATE;
        CreatedGames.add(gameMode);
    }

    public static GameConnector getGameFromName(String name) throws Exception{
        GameConnector matchingGame = null;
        for (GameConnector game:
             registeredGames) {
            if(game.getGameName().toLowerCase().equals(name.toLowerCase())){
                matchingGame = game;
            }
        }
        if(matchingGame != null){
            return matchingGame;
        }else{
            throw new Exception("Game with that name does not exists");
        }
    }

    public static List<GameConnector> getCreatedGames() {
        return CreatedGames;
    }
}

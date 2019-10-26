package nl.bramkoene.minigameapi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.*;

public class
GameController implements Listener {
    private static List<GameConnector> CreatedGames = new ArrayList<>();
    public static List<GameConnector> registeredGames = new ArrayList<>();

    public static void RegisterGamemode(GameConnector gameMode){
        registeredGames.add(gameMode);
        Bukkit.getLogger().info("Registering new gamemode: " + gameMode.getGameName());
    }

    public static void CreateGame(GameConnector gameMode){
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
}

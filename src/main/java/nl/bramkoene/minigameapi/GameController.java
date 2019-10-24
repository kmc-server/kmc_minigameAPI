package nl.bramkoene.minigameapi;

import org.bukkit.event.Listener;

import java.util.*;

public class
GameController implements Listener {
    public MiniGameAPI plugin;
    public static List<GameConnector> CreatedGames = new ArrayList<>();
    public static List<GameConnector> registeredGames = new ArrayList<>();

    public static void RegisterGamemode(GameConnector gameMode){
        registeredGames.add(gameMode);
    }

    public static void CreateGame(GameConnector gameMode){
        CreatedGames.add(gameMode);
    }

    public static GameConnector getGameFromName(String name) throws Exception{
        for (GameConnector game:
             registeredGames) {
            if(game.getGameName().toLowerCase() == name.toLowerCase()){
                return game;
            }
        }
        throw new Exception("Game with that name not found!");
    }
}

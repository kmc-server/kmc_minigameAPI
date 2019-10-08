package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Events.PlayerEventHandler;
import nl.bramkoene.minigameapi.Executors.CreateGame;
import nl.bramkoene.minigameapi.Executors.JoinGame;
import nl.bramkoene.minigameapi.Executors.SetGameSpawnPoints;
import nl.bramkoene.minigameapi.Executors.SetLobbySpawnPoints;
import nl.bramkoene.minigameapi.PlayerData.PlayerManager;
import nl.bramkoene.minigameapi.messages.titles;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class MiniGameAPI extends JavaPlugin {

    public HashMap<UUID,PlayerManager> playermanager = new HashMap<UUID,PlayerManager>();
    public GameManager gameManager;
    public ConfigManager configManager;
    public titles titles;
    public static MiniGameAPI miniGameAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instanceClasses();
        miniGameAPI = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void instanceClasses(){
        this.configManager = new ConfigManager(this);
        this.gameManager = new GameManager(this);
        this.titles = new titles();

        getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);

        this.getCommand("setarenaspawnpoint").setExecutor(new SetGameSpawnPoints(this));
        this.getCommand("setlobbyspawnpoint").setExecutor(new SetLobbySpawnPoints(this));
        this.getCommand("creategame").setExecutor(new CreateGame(this));
        this.getCommand("joingame").setExecutor(new JoinGame(this));
    }

    public static MiniGameAPI getMiniGameAPI() {
        return miniGameAPI;
    }

    public static void setMiniGameAPI(MiniGameAPI miniGameAPI) {
        MiniGameAPI.miniGameAPI = miniGameAPI;
    }

    public HashMap<UUID, PlayerManager> getPlayermanager() {
        return playermanager;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
    public ConfigManager getConfigManager() {
        return configManager;
    }
    public nl.bramkoene.minigameapi.messages.titles getTitles() {
        return titles;
    }
}

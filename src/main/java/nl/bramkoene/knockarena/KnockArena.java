package nl.bramkoene.knockarena;

import nl.bramkoene.knockarena.Events.PlayerEventHandler;
import nl.bramkoene.knockarena.Executors.CreateGame;
import nl.bramkoene.knockarena.Executors.JoinGame;
import nl.bramkoene.knockarena.Executors.SetGameSpawnPoints;
import nl.bramkoene.knockarena.Executors.SetLobbySpawnPoints;
import nl.bramkoene.knockarena.PlayerData.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class KnockArena extends JavaPlugin {

    public HashMap<UUID,PlayerManager> playermanager = new HashMap<UUID,PlayerManager>();
    public GameManager gameManager;
    public ConfigManager configManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instanceClasses();
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);
        this.getCommand("setarenaspawnpoint").setExecutor(new SetGameSpawnPoints(this));
        this.getCommand("setlobbyspawnpoint").setExecutor(new SetLobbySpawnPoints(this));
        this.getCommand("creategame").setExecutor(new CreateGame(this));
        this.getCommand("joingame").setExecutor(new JoinGame(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void instanceClasses(){
        this.configManager = new ConfigManager(this);
        this.gameManager = new GameManager(this);
    }

    public HashMap<UUID, PlayerManager> getPlayermanager() {
        return playermanager;
    }

    public void setPlayermanager(HashMap<UUID, PlayerManager> playermanager) {
        this.playermanager = playermanager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }
}

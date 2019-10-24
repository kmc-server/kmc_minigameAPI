package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Events.PlayerEventHandler;
import nl.bramkoene.minigameapi.Executors.CreateGame;
import nl.bramkoene.minigameapi.Executors.JoinGame;
import nl.bramkoene.minigameapi.GameCreation.GameCreatorCommand;
import nl.bramkoene.minigameapi.GameCreation.SetGameSpawnPoints;
import nl.bramkoene.minigameapi.GameCreation.SetLobbySpawnPoints;
import nl.bramkoene.minigameapi.messages.titles;
import nl.bramkoene.minigameapi.teams.JoinTeamCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MiniGameAPI extends JavaPlugin {

    private GameController gameController;
    private ConfigManager configManager;
    private titles titles;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instanceClasses();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void instanceClasses(){
        this.configManager = new ConfigManager(this);
        this.gameController = new GameController();
        this.titles = new titles();

        getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);

        // Game Building Commmands
        Objects.requireNonNull(this.getCommand("setarenaspawnpoint")).setExecutor(new SetGameSpawnPoints(this));
        Objects.requireNonNull(this.getCommand("setlobbyspawnpoint")).setExecutor(new SetLobbySpawnPoints(this));
        Objects.requireNonNull(this.getCommand("buildminigame")).setExecutor(new GameCreatorCommand(this));

        // Game Playing Commands
        Objects.requireNonNull(this.getCommand("creategame")).setExecutor(new CreateGame(this));
        Objects.requireNonNull(this.getCommand("joingame")).setExecutor(new JoinGame(this));

        // Team Commands
        Objects.requireNonNull(this.getCommand("jointeam")).setExecutor(new JoinTeamCommand(this));


    }
    public ConfigManager getConfigManager() {
        return configManager;
    }
    public nl.bramkoene.minigameapi.messages.titles getTitles() {
        return titles;
    }
}

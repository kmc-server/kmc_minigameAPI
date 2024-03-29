package nl.bramkoene.minigameapi;

import nl.bramkoene.minigameapi.Events.ChatClickEventCallback;
import nl.bramkoene.minigameapi.Events.PlayerEventHandler;
import nl.bramkoene.minigameapi.Executors.CreateGame;
import nl.bramkoene.minigameapi.Executors.JoinGame;
import nl.bramkoene.minigameapi.Executors.getMinigames;
import nl.bramkoene.minigameapi.GameCreation.ChatInteractionGameBuilding;
import nl.bramkoene.minigameapi.GameCreation.GameCreatorCommand;
import nl.bramkoene.minigameapi.GameCreation.SetGameSpawnPoints;
import nl.bramkoene.minigameapi.GameCreation.SetLobbySpawnPoints;
import nl.bramkoene.minigameapi.messages.titles;
import nl.bramkoene.minigameapi.misc.AutoTabCompleter;
import nl.bramkoene.minigameapi.teams.JoinTeamCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MiniGameAPI extends JavaPlugin {

    private ConfigManager configManager;
    private titles titles;
    private static MiniGameAPI miniGameAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        miniGameAPI = this;
        instanceClasses();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void instanceClasses(){
        this.configManager = new ConfigManager(this);
        GameController gameController = new GameController();
        this.titles = new titles();

        getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);
        getServer().getPluginManager().registerEvents(new ChatInteractionGameBuilding(), this);
        // General info
        Objects.requireNonNull(this.getCommand("getminigames")).setExecutor(new getMinigames(this));

        // Game Building Commands
        Objects.requireNonNull(this.getCommand("setarenaspawnpoint")).setExecutor(new SetGameSpawnPoints(this));
        Objects.requireNonNull(this.getCommand("setlobbyspawnpoint")).setExecutor(new SetLobbySpawnPoints(this));
        Objects.requireNonNull(this.getCommand("buildminigame")).setExecutor(new GameCreatorCommand(this));

        // Game Playing Commands
        Objects.requireNonNull(this.getCommand("creategame")).setExecutor(new CreateGame(this));
        Objects.requireNonNull(this.getCommand("joingame")).setExecutor(new JoinGame(this));

        // Team Commands
        Objects.requireNonNull(this.getCommand("jointeam")).setExecutor(new JoinTeamCommand(this));

        // Callback command used for callback functions in chat click events
        Objects.requireNonNull(this.getCommand("minigameapi_callback_command")).setExecutor(new ChatClickEventCallback());

        // Tab Completer
        Objects.requireNonNull(this.getCommand("buildminigame")).setTabCompleter(new AutoTabCompleter());
    }
    public ConfigManager getConfigManager() {
        return configManager;
    }
    public nl.bramkoene.minigameapi.messages.titles getTitles() {
        return titles;
    }

    public static MiniGameAPI getMiniGameAPI() {
        return miniGameAPI;
    }
}

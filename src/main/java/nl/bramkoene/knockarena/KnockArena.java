package nl.bramkoene.knockarena;

import nl.bramkoene.knockarena.Events.PlayerEventHandler;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public final class KnockArena extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Server Server(){
        return getServer();
    }
}

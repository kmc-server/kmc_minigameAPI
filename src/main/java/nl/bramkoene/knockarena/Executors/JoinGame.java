package nl.bramkoene.knockarena.Executors;

import nl.bramkoene.knockarena.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinGame implements CommandExecutor {
    public MiniGameAPI plugin;
    public JoinGame(MiniGameAPI plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
            plugin.getGameManager().lobbyWait((Player) sender);
        }

        return false;
    }
}

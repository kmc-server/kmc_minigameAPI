package nl.bramkoene.knockarena.Executors;

import nl.bramkoene.knockarena.KnockArena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinGame implements CommandExecutor {
    public KnockArena plugin;
    public JoinGame(KnockArena plugin){
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

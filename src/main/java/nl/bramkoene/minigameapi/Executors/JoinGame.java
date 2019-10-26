package nl.bramkoene.minigameapi.Executors;

import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinGame implements CommandExecutor {
    private final MiniGameAPI plugin;
    public JoinGame(MiniGameAPI plugin){
        this.plugin = plugin;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender instanceof Player){
//            plugin.getGameController().lobbyWait((Player) sender);
//            plugin.getTitles().sendTitleToAllPlayers("The game has now: " + plugin.getGameController().joinedPlayers.size(), "To join use /joingame");
        }

        return false;
    }
}

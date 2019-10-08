package nl.bramkoene.knockarena.Executors;

import nl.bramkoene.knockarena.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbySpawnPoints implements CommandExecutor {
    public MiniGameAPI plugin;
    public SetLobbySpawnPoints(MiniGameAPI pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if((sender.isOp() || sender.hasPermission("KnockArena.Builder") || sender.getName() == "minestarnl") && sender instanceof Player){
            Player player = (Player)sender;
                plugin.getConfigManager().getCollectors().set("LobbySpawn.world", player.getLocation().getWorld().getName());
                plugin.getConfigManager().getCollectors().set("LobbySpawn.x", player.getLocation().getX());
                plugin.getConfigManager().getCollectors().set("LobbySpawn.y", player.getLocation().getY());
                plugin.getConfigManager().getCollectors().set("LobbySpawn.z", player.getLocation().getZ());
                plugin.getConfigManager().saveCollectors();
            }
        return false;
    }
}
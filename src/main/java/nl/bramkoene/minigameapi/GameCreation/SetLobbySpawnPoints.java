package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbySpawnPoints implements CommandExecutor {
    private final MiniGameAPI plugin;
    public SetLobbySpawnPoints(MiniGameAPI pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if((sender.isOp() || sender.hasPermission("MiniGameApi.Builder") || sender.getName().equals("minestarnl")) && sender instanceof Player){
            Player player = (Player)sender;
                plugin.getConfigManager().getCollectors().set("LobbySpawn", player.getLocation());
                plugin.getConfigManager().saveCollectors();
            }
        return false;
    }
}
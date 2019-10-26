package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SetGameSpawnPoints implements CommandExecutor {
    private final MiniGameAPI plugin;
    public SetGameSpawnPoints(MiniGameAPI pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if((sender.isOp() || sender.hasPermission("MiniGameApi.Builder") || sender.getName().equals("minestarnl")) && sender instanceof Player){
            Player player = (Player)sender;
            List<Location> locations = new ArrayList<>();
            if(plugin.getConfigManager().getCollectors().contains("GameSpawn")) {
                @SuppressWarnings("unchecked") List<Location> locs = (List<Location>) plugin.getConfigManager().getCollectors().getList("GameSpawn");
                locations.addAll(locs);
            }
            locations.add(player.getLocation());
            plugin.getConfigManager().getCollectors().set("GameSpawn", locations);
            plugin.getConfigManager().saveCollectors();
        }

        return false;
    }
}

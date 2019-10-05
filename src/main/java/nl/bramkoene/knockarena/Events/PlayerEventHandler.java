package nl.bramkoene.knockarena.Events;

import nl.bramkoene.knockarena.KnockArena;
import nl.bramkoene.knockarena.PlayerData.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class PlayerEventHandler implements Listener {
    public KnockArena plugin;
    public PlayerEventHandler(KnockArena pl){
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        event.setKeepInventory(true);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if(event.getPlayer().getWorld().getBlockAt(event.getTo()).getType() == Material.LAVA){
//            event.getPlayer().teleport(getSpawnLocation());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();

        UUID uuid = player.getUniqueId();

        plugin.playermanager.put(uuid,new PlayerManager(uuid, false, 0, false));
    }
}

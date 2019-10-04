package nl.bramkoene.knockarena.Events;

import nl.bramkoene.knockarena.KnockArena;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEventHandler implements Listener {
    public KnockArena plugin;
    public PlayerEventHandler(KnockArena pl){
        this.plugin = pl;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        event.setKeepInventory(true);
    }

    public void onPlayerMove(PlayerMoveEvent event){
        if(event.getPlayer().getWorld().getBlockAt(event.getTo()).getType() == Material.LAVA){
//            event.getPlayer().teleport(getSpawnLocation());
        }
    }
}

package nl.bramkoene.minigameapi.Events;

import org.bukkit.Color;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignClickHandler implements Listener {
    @EventHandler
    public void onSignClick(PlayerInteractEvent event){
        if(event.getClickedBlock().getState() instanceof Sign){
            Sign sign = (Sign) event.getClickedBlock().getState();
            if(sign.getLine(0).equals(Color.BLUE + "(Minigame)")){
                // This is an minigame sign
                String gameName = sign.getLine(1);
                String uniqueName = sign.getLine(2);

            }
        }
    }

}

package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.Enums.BuildGameState;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChatInteractionGameBuilding implements Listener {
    @EventHandler
    public boolean onChatEvent(AsyncPlayerChatEvent event){
        // First check whether the player is actually building a minigame
        if(!BuildMinigame.isBuildingGame(event.getPlayer())){
            return false;
        }

        GameConfigBean gameConfigBean = BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer());
        switch (gameConfigBean.getState()){
            case DEFAULT:
                return false;
            case CHOOSING_NAME:
                gameConfigBean.setUniqueName(event.getMessage());
                try{
                    BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.CHOOSING_ALLOW_RESPAWN);
                }catch(Exception e){
                    Bukkit.getLogger().warning(e.getMessage());
                    e.printStackTrace();
                }

                cancelChatEvent(event);
                return false;
            case CHOOSING_ALLOW_RESPAWN:
                if(event.getMessage().contains("true")){
                    gameConfigBean.setAllowRespawn(true);
                }else if(event.getMessage().contains("false")){
                    gameConfigBean.setAllowRespawn(false);
                }
                try{
                    BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.CHOOSING_MAX_PLAYERS);
                }catch(Exception e){
                    Bukkit.getLogger().warning(e.getMessage());
                    e.printStackTrace();
                }

                cancelChatEvent(event);
                return false;
            case CHOOSING_MAX_PLAYERS:

                try{
                    gameConfigBean.setMinPlayers(Integer.parseInt(event.getMessage()));
                    BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.CHOOSING_TEAM_SIZE);
                }catch(Exception e){
                    Bukkit.getLogger().warning(e.getMessage());
                    e.printStackTrace();
                }

                cancelChatEvent(event);
                return false;
            case CHOOSING_TEAM_SIZE:
                try{
                    gameConfigBean.setTeamPlayers(Integer.parseInt(event.getMessage()));
                    BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.CHOOSING_TEAM_SIZE);
                }catch(Exception e){
                    Bukkit.getLogger().warning(e.getMessage());
                    e.printStackTrace();
                }

                cancelChatEvent(event);
                return false;
            case SETTING_LOBBY_SPAWN_POINT:
                if(event.getMessage().contains("here")){
                    gameConfigBean.setLobbyLocation(event.getPlayer().getLocation());
                    try{
                        BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.SETTING_GAME_SPAWN_POINTS);
                    }catch(Exception e){
                        Bukkit.getLogger().warning(e.getMessage());
                        e.printStackTrace();
                    }
                }

                cancelChatEvent(event);
                return false;
            case SETTING_GAME_SPAWN_POINTS:
                if(event.getMessage().contains("here")){
                    gameConfigBean.addGameLocation(event.getPlayer().getLocation());
                }

                if(event.getMessage().contains("done")){
                    try{
                        BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.SETTING_KIT);
                    }catch(Exception e){
                        Bukkit.getLogger().warning(e.getMessage());
                        e.printStackTrace();
                    }
                }

                cancelChatEvent(event);
                return false;

        }
        return true;
    }

    @EventHandler
    public boolean onInteract(PlayerInteractEvent event){
        if(!(BuildMinigame.isBuildingGame(event.getPlayer()))) return false;

        if(BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer()).getState() == BuildGameState.SETTING_KIT){
            event.getPlayer().sendMessage(event.getClickedBlock().getState().toString());
            if(!(event.getClickedBlock().getState() instanceof Chest)){
                event.getPlayer().sendMessage("Please use this on a chest");
                return false;
            }
            Chest chest = (Chest) event.getClickedBlock().getState();
            BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer()).setKit(chest.getBlockInventory().getContents());
            event.getPlayer().sendMessage("Set kit to chest located at: " + chest.getBlock().getLocation().toString());
            cancelChatEvent(event);
            try{
                BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.PLACING_SIGN);
            }catch(Exception e){
                Bukkit.getLogger().warning(e.getMessage());
                e.printStackTrace();
            }
        }

        return true;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(!BuildMinigame.isBuildingGame(event.getPlayer())) return;

        if(BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer()).getState() == BuildGameState.PLACING_SIGN && event.getBlockPlaced().getState() instanceof Sign){
            Sign sign = (Sign) event.getBlock().getState();
            sign.setLine(0, Color.BLUE + "(Minigame)");
            sign.setLine(1, BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer()).getGameName());
            sign.setLine(2, BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer()).getUniqueName());
            sign.setLine(3, "Click here to join");
            try {
                GameConfigBean game = BuildMinigame.getGameConfigBeanFromPlayer(event.getPlayer());
                game.setSignLocation(event.getBlock().getLocation());
                BuildMinigame.gamesBeingBuild.put(event.getPlayer(), game);
                BuildMinigame.setBuildGameState(event.getPlayer(), BuildGameState.SAVE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Cancels the given chat event
    private void cancelChatEvent(Cancellable event){
        event.setCancelled(true);
    }
}

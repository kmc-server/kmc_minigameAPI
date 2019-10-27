package nl.bramkoene.minigameapi.GameCreation;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import nl.bramkoene.minigameapi.Enums.BuildGameState;
import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.MiniGameAPI;
import nl.bramkoene.minigameapi.messages.GameMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BuildMinigame {
    public static HashMap<Player, GameConfigBean> gamesBeingBuild = new HashMap<>();

    public static boolean isBuildingGame(Player player){
        return gamesBeingBuild.containsKey(player);
    }

    public static GameConfigBean getGameConfigBeanFromPlayer(Player player){
        return gamesBeingBuild.get(player);
    }

    public static void addPlayerToBuildingList(GameConnector gameConnector, Player player){
        GameConfigBean gameConfigBean = new GameConfigBean();
        gameConfigBean.setGameName(gameConnector.getGameName());
        gamesBeingBuild.putIfAbsent(player, gameConfigBean);
    }

    public static void setBuildGameState(Player player, BuildGameState gameState) throws Exception{
        if(!gamesBeingBuild.containsKey(player)){
            throw new Exception("entry with that key does not exists");
        }
        GameConfigBean gameConfigBean = gamesBeingBuild.get(player);
        gameConfigBean.setState(gameState);
        gamesBeingBuild.put(player, gameConfigBean);
        try{
            GameMessages gameMessages = MiniGameAPI.getMiniGameAPI().getConfigManager().getGameMessages();
            switch (gameState){
                case CHOOSING_NAME:
//                    player.sendMessage(gameMessages.getGameUniqueNameMessage());
                    player.sendMessage(ChatColor.YELLOW + "To start please enter a unique name for this arena");
                    break;
                case SETTING_KIT:
//                player.sendMessage(gameMessages);
                    sendDivider(player);
                    player.sendMessage(ChatColor.YELLOW + "Please click on a chest containing the items for the kit");
                    break;
                case SETTING_LOBBY_SPAWN_POINT:
//                    player.sendMessage(gameMessages.getLobbyLocationMessage());
                    sendDivider(player);
                    player.sendMessage(ChatColor.YELLOW + "Please go to the lobby spawn point and chat 'here'");
                    break;
                case SETTING_GAME_SPAWN_POINTS:
//                    player.sendMessage(gameMessages.getGameLocationMessage());
                    sendDivider(player);
                    player.sendMessage(ChatColor.YELLOW + "To set a game location go to the location and type 'here'. Do this for every location you want players to be able to spawn. After you finish type 'done'.");
                    break;
                case PLACING_SIGN:
                    sendDivider(player);
                    player.sendMessage(ChatColor.YELLOW + "Please place an oak sign on the place where you want the Join sign to be");
                    break;
                case CHOOSING_TEAM_SIZE:
                    sendDivider(player);
                    player.sendMessage(ChatColor.YELLOW + "Please enter the amount of players that go in 1 team");
                    break;
                case CHOOSING_MIN_PLAYERS:
                    sendDivider(player);
                    player.sendMessage(ChatColor.YELLOW + "Please enter the amount of players that are required for the game to start");
                    break;
                case CHOOSING_ALLOW_RESPAWN:
                    sendDivider(player);
                    ComponentBuilder message1 = new ComponentBuilder("Do you want players to respawn?").color(net.md_5.bungee.api.ChatColor.YELLOW);
                    ComponentBuilder message2 = new ComponentBuilder("Select " ).color(net.md_5.bungee.api.ChatColor.YELLOW);
                    message2.append("Yes").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minigameapi_callback_command selectRespawn TRUE")).color(net.md_5.bungee.api.ChatColor.GREEN);
                    message2.append(" or ").color(net.md_5.bungee.api.ChatColor.YELLOW);
                    message2.append("No").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minigameapi_callback_command selectRespawn FALSE")).color(net.md_5.bungee.api.ChatColor.RED);
                    player.spigot().sendMessage(message1.create());
                    player.spigot().sendMessage(message2.create());
                    break;
                case SAVE:
                    sendDivider(player);
//                    player.sendMessage(gameMessages.toString());
                    player.sendMessage(ChatColor.YELLOW + "Succesfully made arena");
                    player.sendMessage(MiniGameAPI.getMiniGameAPI().getConfigManager().saveGameBean(getGameConfigBeanFromPlayer(player)));
                    break;

            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    private static void sendDivider(Player player){
        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.ITALIC + "========================");
    }
}

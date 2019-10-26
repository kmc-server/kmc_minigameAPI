package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.Enums.BuildGameState;
import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.MiniGameAPI;
import nl.bramkoene.minigameapi.messages.GameMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

class BuildMinigame {
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
                    player.sendMessage(ChatColor.YELLOW + "Please click on a chest containing the items for the kit");
                    break;
                case SETTING_LOBBY_SPAWN_POINT:
//                    player.sendMessage(gameMessages.getLobbyLocationMessage());
                    player.sendMessage(ChatColor.YELLOW + "Please go to the lobby spawn point and chat 'here'");
                    break;
                case SETTING_GAME_SPAWN_POINTS:
//                    player.sendMessage(gameMessages.getGameLocationMessage());
                    player.sendMessage(ChatColor.YELLOW + "To set a game location go to the location and type 'here'. Do this for every location you want players to be able to spawn. After you finish type 'done'.");
                    break;
                case SAVE:
//                    player.sendMessage(gameMessages.toString());
                    player.sendMessage(ChatColor.YELLOW + "Succesfully made arena");
                    player.sendMessage(MiniGameAPI.getMiniGameAPI().getConfigManager().saveGameBean(getGameConfigBeanFromPlayer(player)));
                    break;

            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}

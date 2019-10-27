package nl.bramkoene.minigameapi.Events;

import nl.bramkoene.minigameapi.Enums.BuildGameState;
import nl.bramkoene.minigameapi.GameCreation.BuildMinigame;
import nl.bramkoene.minigameapi.GameCreation.GameConfigBean;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClickEventCallback implements CommandExecutor {
    /**
     * This an an command that will only be executed by code. This command gets executed from any text click event
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 1){
            sender.sendMessage("There has been an internal error please contact the it guys and tell them how this happened");
            return true;
        }

        // Player barrier any function before this can be any sender after this only a Player can execute
        if(!(sender instanceof Player)){
            sender.sendMessage("This command must be executed from a player");
            return true;
        }

        /**
         * This comes from {@link BuildMinigame}:73
         */
        if(args[0].equals("selectRespawn")){
            GameConfigBean game = BuildMinigame.getGameConfigBeanFromPlayer((Player) sender);
            if(args[1].equals("TRUE")){
                game.setAllowRespawn(true);
                BuildMinigame.gamesBeingBuild.put((Player) sender, game);
                try {
                    BuildMinigame.setBuildGameState((Player) sender, BuildGameState.CHOOSING_MIN_PLAYERS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(args[1].equals("FALSE")){
                game.setAllowRespawn(false);
                BuildMinigame.gamesBeingBuild.put((Player) sender, game);
                try {
                    BuildMinigame.setBuildGameState((Player) sender, BuildGameState.CHOOSING_MIN_PLAYERS);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else sender.sendMessage("This command has not been succesfull Please contact ict");
        }

        if(args[0].equals("joingame")){
            if(args.length < 2){
                sender.sendMessage("Something internal has gone wrong. Please contact ICT");
            }
        }

        return true;
    }
}

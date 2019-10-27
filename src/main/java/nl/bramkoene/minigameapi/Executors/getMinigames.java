package nl.bramkoene.minigameapi.Executors;

import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.GameController;
import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class getMinigames implements CommandExecutor {
    public getMinigames(MiniGameAPI pl){
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        for(GameConnector game : GameController.registeredGames){
            sender.sendMessage(game.getGameName());
        }
        return true;
    }
}

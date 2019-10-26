package nl.bramkoene.minigameapi.Executors;

import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.GameController;
import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGame implements CommandExecutor {
    private final MiniGameAPI plugin;
    public CreateGame(MiniGameAPI pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length < 1){
            return false;
        }

        GameConnector game;
        try{
            game = GameController.getGameFromName(args[0]);
            GameConnector gameConnector = game.getClass().newInstance();
            GameController.CreateGame(gameConnector);
        }catch(Exception e){
            sender.sendMessage(e.getMessage());
        }



        return true;
    }
}

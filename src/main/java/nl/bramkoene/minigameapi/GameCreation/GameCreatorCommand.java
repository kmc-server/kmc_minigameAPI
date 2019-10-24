package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.GameController;
import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCreatorCommand implements CommandExecutor {
    public MiniGameAPI plugin;
    public GameCreatorCommand(MiniGameAPI pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "This command can only be executed as a Player!!");
            return true;
        }

        if(args.length < 1){
            return false;
        }

        GameConnector game;

        try{
            game = GameController.getGameFromName(args[0]);
        }catch(Exception e){
            sender.sendMessage(ChatColor.RED + e.getMessage());
            return false;
        }



        return true;
    }
}



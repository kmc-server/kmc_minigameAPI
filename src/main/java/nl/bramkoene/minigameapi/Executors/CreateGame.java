package nl.bramkoene.minigameapi.Executors;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import nl.bramkoene.minigameapi.GameConnector;
import nl.bramkoene.minigameapi.GameController;
import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGame implements CommandExecutor {
    public CreateGame(MiniGameAPI pl){
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

            ComponentBuilder message1 = new ComponentBuilder("A new " + game.getGameName() + " has started").color(net.md_5.bungee.api.ChatColor.YELLOW);
            ComponentBuilder message2 = new ComponentBuilder("Click " ).color(net.md_5.bungee.api.ChatColor.YELLOW);
            message2.append("here").event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/minigameapi_callback_command joingame " + game.getGameName())).color(net.md_5.bungee.api.ChatColor.GREEN);
            message2.append(" to join").color(net.md_5.bungee.api.ChatColor.YELLOW);
            for(Player player : Bukkit.getServer().getOnlinePlayers()){
                player.spigot().sendMessage(message1.create());
                player.spigot().sendMessage(message2.create());
            }
        }catch(Exception e){
            sender.sendMessage(e.getMessage());
        }



        return true;
    }
}

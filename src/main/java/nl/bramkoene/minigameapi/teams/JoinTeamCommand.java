package nl.bramkoene.minigameapi.teams;

import nl.bramkoene.minigameapi.MiniGameAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinTeamCommand implements CommandExecutor {
    MiniGameAPI plugin;
    public JoinTeamCommand(MiniGameAPI pl){
        this.plugin = pl;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length < 1){
            return false;
        }
        for (Player player: sender.getServer().getOnlinePlayers()) {
            if(player.getName().equals(args[0])){
                try{
                    TeamManager.joinTeam((Player) sender, player);
                }catch(Exception e){
                    sender.sendMessage(e.getMessage());
                }

            }
        }
        sender.sendMessage(ChatColor.YELLOW + "No player with that name found please try again");
        return true;
    }
}

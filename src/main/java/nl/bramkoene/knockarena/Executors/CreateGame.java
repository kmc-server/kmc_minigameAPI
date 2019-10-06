package nl.bramkoene.knockarena.Executors;

import nl.bramkoene.knockarena.KnockArena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateGame implements CommandExecutor {
    public KnockArena plugin;
    public CreateGame(KnockArena pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(sender.isOp()){
            Player player = (Player)sender;
            plugin.getGameManager().setupGame();
        }

        return false;
    }
}
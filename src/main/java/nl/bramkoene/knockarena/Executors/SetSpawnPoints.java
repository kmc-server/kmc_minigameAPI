package nl.bramkoene.knockarena.Executors;

import nl.bramkoene.knockarena.KnockArena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetSpawnPoints implements CommandExecutor {
    public KnockArena plugin;
    public SetSpawnPoints(KnockArena pl){
        this.plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        return false;
    }
}

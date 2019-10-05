package nl.bramkoene.knockarena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManager implements Listener {
    public KnockArena plugin;
    public GameManager(KnockArena pl){
        this.plugin = pl;
    }

    private int lobbyCountDown = 10;
    private int playerNeeded = 1;

    public int getLobbyCountDown() {
        return lobbyCountDown;
    }

    public void setLobbyCountDown(int lobbyCountDown) {
        this.lobbyCountDown = lobbyCountDown;
    }

    public int getPlayerNeeded() {
        return playerNeeded;
    }

    public void setPlayerNeeded(int playerNeeded) {
        this.playerNeeded = playerNeeded;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    private boolean isStarted;

    Location lobbySpawn;
    Location gameSpawn;

    public void setupGame(){

        this.gameSpawn = new Location(
                Bukkit.getServer().getWorld(plugin.configManager.collectorscfg.getString("GameSpawn.world")),
                plugin.configManager.collectorscfg.getDouble("GameSpawn.x"),
                plugin.configManager.collectorscfg.getDouble("GameSpawn.y"),
                plugin.configManager.collectorscfg.getDouble("GameSpawn.z")
        );

        this.lobbySpawn = new Location(
                Bukkit.getServer().getWorld(plugin.configManager.collectorscfg.getString("LobbySpawn.world")),
                plugin.configManager.collectorscfg.getDouble("LobbySpawn.x"),
                plugin.configManager.collectorscfg.getDouble("LobbySpawn.y"),
                plugin.configManager.collectorscfg.getDouble("LobbySpawn.z")
        );

    }

    public void lobbyWait(Player player){
        int online = Bukkit.getOnlinePlayers().size();

        Bukkit.getServer().broadcastMessage("TODO");
    }

    public void playerCheck(int online){
        new BukkitRunnable() {
            @Override
            public void run(){
                if(online >= playerNeeded){
                    lobbyCountDown();
                    setStarted(true);
                    this.cancel();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20l);
    }

    public void gameStart(){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.teleport(gameSpawn);
            plugin.getPlayermanager().get(player.getUniqueId()).setInGame(true);
        }
    }

    public void gameStop(){

    }

    public void lobbyCountDown(){
        new BukkitRunnable() {
            @Override
            public void run(){
                if(lobbyCountDown > 0){
                    lobbyCountDown = lobbyCountDown -1;
                }else{
                    gameStart();
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20l);
    }

}

package nl.bramkoene.knockarena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager implements Listener {
    public KnockArena plugin;
    protected Location lobbySpawn;
    protected List<Location> gameSpawn = new ArrayList<Location>();
    private int lobbyCountDown = 10;
    private int playerNeeded = 1;
    private boolean isStarted;
    private boolean isInLobby;

    public GameManager(KnockArena pl){
        this.plugin = pl;
    }

    public void setupGame(){
        List<Location> locs = (List<Location>) plugin.getConfigManager().getCollectors().getList("GameSpawn");
        for (Location location: locs) {
            this.gameSpawn.add(location);
        }

        this.lobbySpawn = new Location(
                plugin.getServer().getWorld(plugin.configManager.collectorscfg.getString("LobbySpawn.world")),
                plugin.configManager.collectorscfg.getDouble("LobbySpawn.x"),
                plugin.configManager.collectorscfg.getDouble("LobbySpawn.y"),
                plugin.configManager.collectorscfg.getDouble("LobbySpawn.z")
        );
        this.setLobbyCountDown(20);
        this.isInLobby = true;
    }

    public void lobbyWait(Player player){
        int online = Bukkit.getOnlinePlayers().size();

        Bukkit.getServer().broadcastMessage("TODO");

        playerCheck(0);

        player.teleport(lobbySpawn);
    }

    public void playerCheck(int online){
        if(online >= playerNeeded){
            lobbyCountDown();
            setStarted(true);
        }
    }

    public void gameStart(){
        for(Player player : Bukkit.getOnlinePlayers()){
            plugin.getPlayermanager().get(player.getUniqueId()).setInGame(true);
        }
    }

    public void spawnPlayer(Player player){
        Random rand = new Random();
        int n = rand.nextInt(gameSpawn.size());
        player.teleport(gameSpawn.get(n));
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

    public boolean isInLobby() {
        return isInLobby;
    }

}

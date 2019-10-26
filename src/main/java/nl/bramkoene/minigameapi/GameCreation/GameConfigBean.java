package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.Enums.BuildGameState;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameConfigBean implements Serializable {
    private String uniqueName;
    private String gameName;
    private List<Location> gameLocations = new ArrayList<>();
    private Location lobbyLocation;
    private ItemStack[] kit;
    private BuildGameState state;
    private int minPlayers;
    private int teamPlayers;
    private boolean allowRespawn;
    private Location signLocation;

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<Location> getGameLocations() {
        return gameLocations;
    }

    public void setGameLocations(List<Location> gameLocations) {
        this.gameLocations = gameLocations;
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public void setLobbyLocation(Location lobbyLocation) {
        this.lobbyLocation = lobbyLocation;
    }

    public ItemStack[] getKit() {
        return kit;
    }

    public void setKit(ItemStack[] kit) {
        this.kit = kit;
    }

    public BuildGameState getState() {
        return state;
    }

    public void setState(BuildGameState state) {
        this.state = state;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(int teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public boolean isAllowRespawn() {
        return allowRespawn;
    }

    public void setAllowRespawn(boolean allowRespawn) {
        this.allowRespawn = allowRespawn;
    }

    public Location getSignLocation() {
        return signLocation;
    }

    public void setSignLocation(Location signLocation) {
        this.signLocation = signLocation;
    }

    public void addGameLocation(Location gamelocation){
        this.gameLocations.add(gamelocation);
    }
}

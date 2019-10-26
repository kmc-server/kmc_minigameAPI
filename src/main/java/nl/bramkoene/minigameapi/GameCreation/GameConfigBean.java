package nl.bramkoene.minigameapi.GameCreation;

import nl.bramkoene.minigameapi.Enums.BuildGameState;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameConfigBean implements Serializable {
    private String uniqueName;
    private String gameName;
    private List<Location> gameLocations = new ArrayList<>();
    private Location lobbyLocation;
    private ItemStack[] kit;
    private BuildGameState state;

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

    public void addGameLocation(Location gamelocation){
        this.gameLocations.add(gamelocation);
    }
}

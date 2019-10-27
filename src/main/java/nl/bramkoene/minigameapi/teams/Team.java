package nl.bramkoene.minigameapi.teams;

import nl.bramkoene.minigameapi.Enums.GameState;
import nl.bramkoene.minigameapi.GameConnector;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private final List<Player> players = new ArrayList<>();
    protected GameState gameState = GameState.FREE;
    protected GameConnector currentGame;

    public void addPlayer(Player player){
        players.add(player);
        TeamManager.playerTeamHashMap.put(player, this);
    }

    public void setLeader(Player leader){
    }
}

package nl.bramkoene.minigameapi.teams;

import nl.bramkoene.minigameapi.Enums.GameState;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    protected String name;
    protected List<Player> players = new ArrayList<Player>();
    protected GameState gameState = GameState.FREE;

    public Team(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player){
        players.add(player);
        TeamManager.playerTeamHashMap.put(player, this);
    }
}

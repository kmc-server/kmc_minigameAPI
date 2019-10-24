package nl.bramkoene.minigameapi.teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {
    public static List<Team> teams = new ArrayList<Team>();
    public static HashMap<Player, Team> playerTeamHashMap = new HashMap<>();

    public static void createTeam(Player sender){
        Team team = new Team();
        team.addPlayer(sender);
        team.setLeader(sender);
    }

    public static Team getUserTeam(Player player){
        return playerTeamHashMap.get(player);
    }

    public static void joinTeam(Player sender, Player leader) throws Exception{
        if(!playerTeamHashMap.containsKey(leader)){
            throw new Exception("User does not exists");
        }
        Team team = playerTeamHashMap.get(leader);
        team.addPlayer(sender);
    }
}

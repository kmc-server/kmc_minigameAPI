package nl.bramkoene.minigameapi.teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamManager {
    public static List<Team> teams = new ArrayList<>();
    public static final HashMap<Player, Team> playerTeamHashMap = new HashMap<>();

    /**
     * Creates a new Team with the given {@link Player} as owner
     * @param sender a {@link Player} that needs a team and becomes the leader of the team.
     */
    public static void createTeam(Player sender){
        Team team = new Team();
        team.addPlayer(sender);
        team.setLeader(sender);
    }

    /**
     * Returns the team that the player is currently a part of
     * @param player The {@link Player}'s team that you need the team of
     * @return {@link Team} that the player is currently a part of
     */
    public static Team getUserTeam(Player player){
        return playerTeamHashMap.get(player);
    }

    /**
     * This adds a Player to a team found by the leader Player param
     * @param sender a {@link Player} that needs to be added to a given team
     * @param leader a {@link Player} that owns a given team where the sender needs to join
     * @throws Exception When a given team leader was not found
     */
    public static void joinTeam(Player sender, Player leader) throws Exception{
        if(!playerTeamHashMap.containsKey(leader)){
            throw new Exception("User does not exists");
        }
        Team team = playerTeamHashMap.get(leader);
        team.addPlayer(sender);
    }
}

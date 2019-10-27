package nl.bramkoene.minigameapi.teams;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
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

    public static void invitePlayer(Player leader, Player target) throws Exception{
        if(!playerTeamHashMap.containsKey(leader)){
            throw new Exception("User does not exists");
        }
        Team team = playerTeamHashMap.get(leader);
        ClickEvent accept = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jointeam " + leader.getDisplayName() + " jajaja");
        target.spigot().sendMessage(new TextComponent(ChatColor.YELLOW + "Someone has invited you to their team"));
        target.spigot().sendMessage(new ComponentBuilder("Click ").color(net.md_5.bungee.api.ChatColor.YELLOW).append("here").event(accept).create());
    }
}

package nl.bramkoene.knockarena.teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamManager {
    public static List<Team> teams = new ArrayList<Team>();

    public void createTeam(Player sender, String name){
        Team team = new Team(name);
        team.addPlayer(sender);
    }

    public boolean hasTeam(String name){
        for (int i = 0; i < teams.size(); i++){
            Team team = teams.get(i);
            if(team.getName() == name){
                return true;
            }

            if(i == teams.size()){
                return false;
            }
        }
        return false;
    }

    public Team getTeamByName(String name){
        for (int i = 0; i < teams.size(); i++){
            Team team = teams.get(i);
            if(team.getName() == name){
                return team;
            }

            if(i == teams.size()){
                new Team("Poep");
            }
        }
        return new Team("test");
    }

    public void joinTeam(Player sender, String name){
        if(hasTeam(name)){
            getTeamByName(name).addPlayer(sender);
        }
    }
}

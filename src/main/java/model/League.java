package model;

import org.json.JSONArray;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *  Autor: FOKO FOTSO
 *
 * The League class content all the necessaries elements objects a league.
 * model
 */

public class League {
    private String leagueName;
    private String season;
    private LocalDate start;
    private LocalDate end;
    private ArrayList<Team> teams;

    public String getLeagueName() {
        return leagueName;
    }

    public String getSeason() {
        return season;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = LocalDate.parse(start);
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = LocalDate.parse(end);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(JSONArray arr) {
        this.teams = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            String name = arr.getJSONObject(i).getString("name");
            System.out.println(name);
            getTeams().add(new Team(name));
        }
    }
}

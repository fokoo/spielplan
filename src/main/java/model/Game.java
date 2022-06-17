package model;

import java.time.LocalDate;

/**
 *  Autor: FOKO FOTSO
 *
 * The Game class content all the necessary attributes objects of a Game.
 * model
 */

public class Game implements Comparable<Game>{
    private Team teamA;
    private Team teamB;
    private LocalDate gameDate;
    private boolean isPlay;

    public Game(Team teamA, Team teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.isPlay = false;
        this.gameDate = LocalDate.now();
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setPlay(boolean play) {
        isPlay = play;
    }

    @Override
    public String toString() {
        return "So " +
                gameDate.toString() +
                " | " +
                teamA.getName() +
                " - " +
                teamB.getName();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int compareTo(Game o) {
        if (this == null || o == null){
            return -2;
        }
        Integer n1 = this.teamA.getNumberOfGame() + this.teamB.getNumberOfGame();
        Integer n2 = o.teamA.getNumberOfGame() + o.teamB.getNumberOfGame();
        return n1.compareTo(n2);
    }
}

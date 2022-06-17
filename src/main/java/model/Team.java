package model;

/**
 *  Autor: FOKO FOTSO
 *
 * The Team class principale of the league.
 * model
 */
public class Team {
    private String name;
    private int numberOfGame;

    public Team(String name){
        this.name = name;
        this.numberOfGame = 0;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfGame() {
        return this.numberOfGame;
    }

    public void addNumberOfGame() {
        this.numberOfGame += 1;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", numberOfGame=" + numberOfGame +
                '}';
    }
}

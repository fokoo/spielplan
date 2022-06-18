import model.Game;
import model.League;
import model.Team;

import java.time.LocalDate;
import java.util.*;

/**
 *  Autor: FOKO FOTSO
 *
 * The GamePlan class is the most impotant class. It content the most important methods
 * for our game plan.
 *
 * controler
 */


public class GamePlan {
    public League league;
    private LocalDate firstSunday;
    private LocalDate lastSunday;

    /**
     * A list to save all the possible games.
     */
    private ArrayList<Game> games;

    /**
     * A list to save all the games with the date.
     */
    private ArrayList<Game> gamePlan;

    /**
     * A list to save the teams that played the day
     */
    private ArrayList<Team> playersOfDay;

    public GamePlan(League league) {
        this.getGamePlan(league);
    }

    /**
     * fill the list games with all the game.
     *
     * @return games
     */
    public ArrayList<Game> setAllGames() {
        ArrayList<Team> teams = league.getTeams();
        this.games = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            Team teamA = teams.get(i);
            for (int j = i+1; j < teams.size(); j++) {
                Team teamB = teams.get(j);
                Game game = new Game(teamA, teamB);
                this.games.add(game);
            }
        }
        return games;
    }

    /**
     * the method sort list games, and set the first/last.
     * Set a factor for the date.
     * Check if the list gameplan is full, and return null if it is the case.
     * Set up the playersday-list.
     * Check the first unplay game, if this first one is not in
     * the playerday-list, then it will be add to the gameplan list,
     * else continuous checking the games list till the end.
     *
     * It's the core method.
     * @return the next game with date
     */
    public Game getNextGame() {
        //System.out.println("Games sorted");
        int currentGame = 0;
        int teamsSize = this.league.getTeams().size();
        int max = 2 * teamsSize - 2;
        this.playersOfDay = new ArrayList<>();
        int factorDate = 0;

        Collections.sort(this.games);
        Game game = this.games.get(currentGame);
        Game last = this.games.get(this.games.size() - 1);

        if (game.getTeamA().getNumberOfGame() == max &&
                game.getTeamB().getNumberOfGame() == max &&
                last.getTeamA().getNumberOfGame() == max &&
                last.getTeamB().getNumberOfGame() == max) {
            return null;
        }

        if (playersOfDay.size() == teamsSize && teamsSize % 2 == 0) {
            this.playersOfDay.clear();
            factorDate = 1;
        } else if (playersOfDay.size() == teamsSize - 1 && teamsSize % 2 == 1) {
            this.playersOfDay.clear();
            factorDate = 1;
        }

        while (game.isPlay() && currentGame < this.games.size()) {
            game = this.games.get(currentGame);
            ++currentGame;
        }
        if (!this.playersOfDay.contains(game.getTeamA()) && !this.playersOfDay.contains(game.getTeamB())) {
            return this.addGame(game, factorDate);
        } else {
            while (!this.playersOfDay.contains(game.getTeamA())
                    && !this.playersOfDay.contains(game.getTeamB())
                    && currentGame < this.games.size()) {
                game = this.games.get(++currentGame);
                if (!this.playersOfDay.contains(game.getTeamA()) && !this.playersOfDay.contains(game.getTeamB())) {
                   return addGame(game, factorDate);
                }
            }
        }
        return null;
    }

    /**
     * method ussed in getNextGame
     */
    private Game addGame(Game game, int factorDate){
        game.setGameDate(this.getNextDate(game.getTeamA().getNumberOfGame() + factorDate));
        games.get(0).getTeamA().addNumberOfGame();
        games.get(0).getTeamB().addNumberOfGame();
        this.playersOfDay.add(game.getTeamA());
        this.playersOfDay.add(game.getTeamB());
        game.setPlay(true);
        return game;
    }

    /**
     *  compute the localdate of the next.
     *  Method is ussed in getNextGame
     *
     * @param  playDay the factor
     * @return         localdate of the game
     */
    public LocalDate getNextDate(int playDay) {
        if(playDay == 0){
            return this.firstSunday;
        }
        if (this.firstSunday.plusDays(7*playDay).isAfter(lastSunday)){
            return lastSunday;
        }
        return this.firstSunday.plusDays(7*playDay);
    }

    /**
     * return the first sunday
     * @param  start  from json
     */
    private void setFirstSunday(LocalDate start){
        int dayOfWeek = start.getDayOfWeek().getValue();
        if(dayOfWeek == 7){
            this.firstSunday = start;
        } else if(dayOfWeek != 7){
            this.firstSunday = start.plusDays(7-dayOfWeek);
        }
    }

    /**
     * return the last sunday
     * @param  end  from json
     */
    private void setLastSunday(LocalDate end){
        int dayOfWeek = end.getDayOfWeek().getValue();
        if(dayOfWeek == 7){
            this.lastSunday = end;
        } else if(dayOfWeek != 7){
            this.lastSunday =  end.minusDays(dayOfWeek);
        }
    }

    /**
     * add a game to the list gamePlan
     *
     * @param  nextGame  from method getNextGame
     */
    public void addNextGame(Game nextGame) {
        if (this.gamePlan == null) {
            this.gamePlan = new ArrayList<>();
        }
        this.gamePlan.add(nextGame);
    }

    /**
     * this is the main of all methods,
     *
     * @param  league   from the json file
     */
    public void getGamePlan(League league) {
        this.league = league;
        this.setAllGames();
        this.setFirstSunday(league.getStart());
        this.setLastSunday(league.getEnd());
        Game nextGame = this.getNextGame();
        while(nextGame != null) {
            this.addNextGame(nextGame);
            nextGame = this.getNextGame();
        }
        this.printGames(gamePlan);
        Util.printOnTextFile(gamePlan, this.league);
    }

    /**
     * ussed for test purpose
     *
     * @param  gamePlan
     */
    public void printGames(ArrayList<Game> gamePlan){
        System.out.println("Size = " + gamePlan.size());
        for (Game game: gamePlan) {
            String strGame = game.toString();
            System.out.println(strGame);
         //   System.out.println(game.getTeamA().toString() +  " " + game.getTeamB().toString() );
        }
    }
}
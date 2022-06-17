import model.League;
import org.json.JSONObject;

import java.io.IOException;


/**
 *  Autor: FOKO FOTSO
 *
 * Main class
 */
public class Main {

    public static void main(String[] args) throws IOException {

        JSONObject obj = Util.getJSONFile(Util.getLinks("./mannschaften.json"));
        League league = new League();
        league.setLeagueName(obj.getString("league"));
        league.setSeason(obj.getString("season"));
        league.setStart(obj.getString("start"));
        league.setEnd(obj.getString("end"));
        league.setTeams(obj.getJSONArray("teams"));
        new GamePlan(league);
    }
}

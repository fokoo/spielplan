import model.Game;
import model.League;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *  Autor: FOKO FOTSO
 *
 * The UTIL class content all the necessary utils.
 */

public class Util {
    public static String getLinks(String fileName) throws IOException {
       return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static JSONObject getJSONFile(String strJson) {
        return new JSONObject(strJson);
    }

    public static void printOnTextFile(ArrayList<Game> gamePlan, League league) {
        try {
            File myObj = new File("./spielPlan.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter("./spielPlan.txt");
            myWriter.write(league.getLeagueName() + " " + league.getSeason());
            myWriter.write(System.lineSeparator());
            myWriter.write(System.lineSeparator());
            for (Game game: gamePlan) {
                myWriter.write(game.toString());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
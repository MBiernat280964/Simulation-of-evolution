package simulation;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Object <code>CSVReader</code> contains function reading from csv files
 */
public class CSVReader {
    /**
     * function read from csv file and saves and returns integers in an arrayList
     * @param path path of csv file that will be read from
     * @return ArrayList containing number of creatures, years od simulation and biome as following: wolf, human, cockroach, dino, bird, fish, years, biome (1-riverside, 2-island, 3-lake)
     */
    public static ArrayList<Integer> readFromCsv(String path)
    {
        try {

            ArrayList<Integer> arList = new ArrayList<>();
            Scanner scanner = new Scanner(new File(path));
            String scanned = scanner.nextLine();
            scanner.close();
            //code below is not necessary, but in some csv files it helps not to catch NumberFormatException
            //scanned = scanned.substring(1, scanned.length());
            String[] tabOfStrings = scanned.split(";");

            for (String s: tabOfStrings) {
                arList.add(Integer.parseInt(s));
            }
            return arList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

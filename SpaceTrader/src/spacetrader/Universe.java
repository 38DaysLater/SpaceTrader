package spacetrader;
import java.util.HashSet;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * This is the universe for the game. Contains solar systems and generates
 * random ones but the player will always start off in the Chocolate Milkyway
 * and on the planet second earth, the only planet in that solar system
 * @author AfiqAzaibi
 */

public class Universe implements Serializable {
        private final int NUM_SOLAR_SYSTEMS = 10;
        private final int WINDOW_WIDTH = 750;
        private final int WINDOW_HEIGHT = 500;
        SolarSystem[] SSArray = new SolarSystem [NUM_SOLAR_SYSTEMS];

        /**
        * Constructor of Solar System.
        * reads in text file and randomly generates solar system coordinates
        * @throws IOException
        */
        public Universe() throws IOException {
            //sets up string of solar system name creation
            String[] solarSystemNames = new String[NUM_SOLAR_SYSTEMS];
            solarSystemNames[0] = "The Chocolate Milkyway";
            try {
                FileReader inputFile = new FileReader("SolarSystemNames.txt");
                BufferedReader bufReader = new BufferedReader(inputFile);
                for (int i = 1; i < NUM_SOLAR_SYSTEMS; i++) {
                    solarSystemNames[i] = bufReader.readLine();
                }
                bufReader.close();
            } catch (IOException e) {
                System.out.println("IOException caught in Universe line 42");
            }

            //randomly generates a solar system name and location such
            // that no two occupy the same space
            HashSet<int[]> coordinates = new HashSet<int[]>();

            //specific only to starting SS
            int[] chocolateMilkyway = new int[2];
            chocolateMilkyway[0] = 0;
            chocolateMilkyway[1] = 0;
            coordinates.add(chocolateMilkyway);
/*
            for (int i = -250; i < 250; i ++) {
                for (int j = -250; j < 250; j ++) {
                        int[] key = new int[2];
                        key[0] = i;
                        key[1] = j;
                        coordinates.add(key);
                }
            }
*/

            Random rand = new Random();
            int x, y;
            int[] key = new int[2];

            for (int i = 1; i < NUM_SOLAR_SYSTEMS; i++) {

                x = rand.nextInt(WINDOW_WIDTH) - (WINDOW_WIDTH / 2);
                y = rand.nextInt(WINDOW_HEIGHT) - (WINDOW_HEIGHT / 2);
                key[0] = x; key[1] = y;

                //makes sure set doesn't have same coordinate
                while (coordinates.contains(key)) {
                        //change memory address
                        key = null;
                        key = new int[2];
                        x = rand.nextInt(WINDOW_WIDTH) - (WINDOW_WIDTH / 2);
                        y = rand.nextInt(WINDOW_HEIGHT) - (WINDOW_HEIGHT / 2);
                        key[0] = x; key[1] = y;
                }
                coordinates.add(key);
/*
                //avoids a 10 pixel perimeter
                for (int a = (x - 250); a < (x + 250); a ++) {
                    for (int b = (y - 250); b < (y + 250); b ++ ) {
                        key = new int[2];
                        key[0] = a;
                        key[1] = b;
                        coordinates.add(key);
                    }
                }
*/
            } //ends random generation of solar systems

            //to make sure no other SS gets those coordinates
            coordinates.remove(chocolateMilkyway);

            //creating a solar system
            SSArray[0] = new SolarSystem(solarSystemNames[0], 0, 0);
            int i = 1;
            for (int[] xy: coordinates) {
                SolarSystem ss = new SolarSystem(solarSystemNames[i],
                        xy[0], xy[1]);
                SSArray[i] = ss;
                i++;
            }
        } //ends the constructor


        @Override
        /**
        * Displays the contents of the solar system.
        * @return String of the contents
        */
        public String toString() {
            String returnString = "";
            for (int i = 0; i < NUM_SOLAR_SYSTEMS; i++) {
                returnString = returnString + SSArray[i].toString();
            }

            return returnString;
        }

        public SolarSystem getSolarSystem(int SSNum) {
            return SSArray[SSNum];
        }

        public SolarSystem[] getAllSolarSystems() {
            return SSArray;
        }
}



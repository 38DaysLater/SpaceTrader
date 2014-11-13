package spacetrader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Random;
import javafx.scene.image.Image;
import java.io.Serializable;

/**
 * This class represents a Solar System.
 * It is instantiated by the Universe class
 * It contains a list of planets, its name,
 * position, size, and number of planets
 * @author lsmoore
 */
public class SolarSystem implements Serializable {
    //CHECKSTYLE: OFF
    private static final long serialVersionUID = 1;
    private static int ssCount = 0;
    private Set<Planet> planetSet;
    private String name;
    private int x, y;
    private static final int SOLARSIZE = 1000;
    private static final int NUM_PLANETS = 5;
    private transient Image pic;
    private double sizeX, sizeY;
    private Random rand = new Random();


    //CHECKSTYLE: ON


/**
 * This is the constructor. Universe passes in the name and coordinates
 * @param name
 * @param x
 * @param y
 */

    public SolarSystem(String name, int x, int y) {
        HashSet<Integer> xHash = new HashSet<Integer>();
        HashSet<Integer> yHash = new HashSet<Integer>();
        HashSet<String> stringHash = new HashSet<String>();

        this.name = name;
        this.x = x;
        this.y = y;
        pic = setSSPic();
        sizeX = pic.getWidth();
        sizeY = pic.getHeight();


        Random rand = new Random();
        planetSet = new HashSet<Planet>();
        Planet planet;

        if (ssCount == 0) {
            planetSet.add(new Planet("Second Earth", 0, 0, this));
        } else {
            //randomely generates the positions, name, and other characteristcs
            // of the planets
            for (int index = 0; index < NUM_PLANETS; index++) {
                planet = null;
                int xpos = rand.nextInt(750) - 375;
                int ypos = rand.nextInt(500) - 250;
                String pname = Planets.values()[rand.nextInt(119)].toString();

                if ((xHash.contains(xpos) && yHash.contains(ypos))
                        || stringHash.contains(pname)) {
                    xpos = rand.nextInt(750) - 375;
                    ypos = rand.nextInt(500) - 250;
                    pname = Planets.values()[rand.nextInt(119)].toString();
                }

                xHash.add(xpos);
                yHash.add(ypos);
                stringHash.add(pname);
                planet = new Planet(pname, xpos, ypos, this);
                planetSet.add(planet);
            }
        }
        ssCount++;
    }

    public Image setSSPic() {
        int option = rand.nextInt(3);
        switch (option) {
            case 0:
                pic = new Image("/spacetrader/resources/isabellesStar.png",
                        63, 61, true, true);
                break;
            case 1:
                pic = new Image("/spacetrader/resources/redStar.png",
                        63, 61, true, true);
                break;
            case 2:
                pic = new Image("/spacetrader/resources/shinystar.png",
                        63, 61, true, true);
                break;
            default:
                pic = new Image("/spacetrader/resources/SolarSystem2.png");
                break;
        }
        return pic;
    }

/**
 * Gets a list of the planets
 * @return a list of the planets
 */

    public List<Planet> getPlanets() {
        ArrayList<Planet> list = new ArrayList<Planet>();
        for (Planet planet: planetSet) {
            list.add(planet);
        }
        return list;
    }

/**
 * returns the name of the solar system.
 * @return name a string of the name
 */
    public String getName() {
        return name;
    }
/**
 * Gets the current planet
 * @param i
 * @return PArray[i]
 */
    public Planet getPlanet(int i) {
        Object[] pArray = planetSet.toArray();
        return (Planet) pArray[i];
    }

    public int[] getLocation() {
        int[] loc = new int[2];
        loc[0] = x;
        loc[1] = y;
        return loc;
    }

    public boolean isHit(double px, double py) {
        if (px >= (x - (sizeX / 2)) && px <= (x + (sizeX / 2))) {
            if (py >= (y - (sizeY / 2)) && py <= (y + (sizeY / 2))) {
                return true;
            }
        }
        return false;
    }
    public Image getSSPic() {
        if (pic == null) {
            pic = new Image("/spacetrader/resources/SolarSystem2.png");
        }
        return pic;
    }
/**
 * Displays the contents of the solar system.
 * @return a string of the contents
 */
    @Override
    public String toString(){
        StringBuffer message = new StringBuffer();
        message.append("Solar System Name: " + name);
        message.append("\nSolar System Position: (" + x + "," + y + ")");
        message.append("\nPlanets:");
        List<Planet> list = getPlanets();
        for (Planet planet: list) {
            message.append("\n\t");
            message.append(planet.toString());
           message.append("\n--------------------------------------\n");
        }
        return message.toString();
    }
    
}

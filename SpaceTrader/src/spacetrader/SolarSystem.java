package spacetrader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.HashSet;
import javafx.scene.image.Image;

/**
 * This class represents a Solar System.  It is instantiated by the Universe class
 * It contains a list of planets, its name, position, size, and number of planets
 * @author lsmoore
 */
public class SolarSystem {
    private static int ssCount = 0;
    private Set<Planet> planetSet;
    private String name;
    private int x, y;
    private final int SOLARSIZE = 1000;
    private final int NUM_PLANETS = 3; 
    private Image pic;
    private double sizeX, sizeY;
        
    
/**
 * This is the constructor. Universe passes in the name and coordinates
 * @param a String of the name and two ints for x and y
 */
 
    
    public SolarSystem(String name, int x, int y) {
        HashSet<Integer> xHash = new HashSet<Integer>();
        HashSet<Integer> yHash = new HashSet<Integer>();
        HashSet<String> stringHash = new HashSet<String>();
        
        this.name = name;
        this.x = x;
        this.y = y;
        pic = new Image("/spacetrader/resources/SolarSystem2.png");
        sizeX = pic.getWidth();
        sizeY = pic.getHeight();
        
        
        Random rand = new Random();
        planetSet = new HashSet<Planet>();
        Planet planet;
        
        if (ssCount == 0) {
            planetSet.add(new Planet("Second Earth", 0,0, this));
        } else{
            //randomely generates the positions, name, and other characteristcs of the planets
            for (int index = 0; index < NUM_PLANETS; index++){
                planet = null;
                int xpos = rand.nextInt(750)-375;
                int ypos = rand.nextInt(500)-250;
                String pname = Planets.values()[rand.nextInt(119)].toString();

                if ((xHash.contains(xpos) && yHash.contains(ypos)) || stringHash.contains(pname)) {
                    xpos = rand.nextInt(750)-375;
                    ypos = rand.nextInt(500)-250;
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
    
/**
 * Gets a list of the planets
 * @param none
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
 * returns the name of the solar system
 * @param none
 * @return a string of the name
 */
    public String getName(){
        return name;
    }
    
    public Planet getPlanet(int i){
        Object[] PArray = planetSet.toArray();
        return (Planet)PArray[i];
    }
    
    public int[] getLocation(){
        int[] loc = new int[2];
        loc[0] = x;
        loc[1] = y;
        return loc;
    }
    
    public boolean isHit(double px, double py) {
        if (px >= (x - (sizeX/2)) && px <= (x + (sizeX/2))) 
            if (py >= (y - (sizeY/2)) && py <= (y + (sizeY/2)))
                return true;
        return false;
    }
    public Image getSSPic() {
        return pic;
    }
/**
 * Displays the contents of the solar system 
 * @param none
 * @return a string of the contents
 */
    public String toString(){
        String message;
        message = "Solar System Name: " + name;
        message = message + "\nSolar System Position: (" + x + "," + y + ")";
        message = message + "\nPlanets:";
        List<Planet> list = getPlanets();
        for (Planet planet: list){
            message = message + "\n\t" + planet.toString();
            message = message + "\n--------------------------------------\n";
        }
        
        return message;
    }
    
}

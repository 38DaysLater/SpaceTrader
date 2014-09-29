package spacetrader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents a Solar System.  It is instantiated by the Universe class
 * It contains a list of planets, its name, position, size, and number of planets
 * @author lsmoore
 */
public class SolarSystem {
    
    private HashSet<Planet> planetSet;
    private String name;
    private int x, y;
    private final int SOLARSIZE = 1000;
    private final int NUM_PLANETS = 9;        
        
    
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
        
        Random rand = new Random();
        planetSet = new HashSet<Planet>();
        
        //randomely generates the positions, name, and other characteristcs of the planets
        for (int index = 0; index < NUM_PLANETS; index++){
            int xpos = rand.nextInt(SOLARSIZE);
            int ypos = rand.nextInt(SOLARSIZE);
            String pname = Planets.values()[rand.nextInt(119)].toString();
            
            if ((xHash.contains(xpos) && yHash.contains(ypos)) || stringHash.contains(pname)) {
                xpos = rand.nextInt(SOLARSIZE);
                ypos = rand.nextInt(SOLARSIZE);
                pname = Planets.values()[rand.nextInt(119)].toString(); 
            }
            
            xHash.add(xpos);
            yHash.add(ypos);
            stringHash.add(pname);
            Planet planet = new Planet(pname, xpos, ypos, this);   
            planetSet.add(planet);
        }
        
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

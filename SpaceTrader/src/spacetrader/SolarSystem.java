package spacetrader;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author lsmoore
 */
public class SolarSystem {
    
    private HashSet<Planet> planetSet;
    private String name;
    private int x, y;
    private final int SOLARSIZE = 1000;
    private final int NUM_PLANETS = 9;        
        
    
    
    
    public SolarSystem(String name, int x, int y) {
        HashSet<Integer> xHash = new HashSet<Integer>();
        HashSet<Integer> yHash = new HashSet<Integer>();
        HashSet<String> stringHash = new HashSet<String>();
        
        this.name = name;
        this.x = x;
        this.y = y;
        
        Random rand = new Random();
        planetSet = new HashSet<Planet>();
        
        //int size = rand.nextInt(2) + 1;
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
    
    public List<Planet> getPlanets() {
        ArrayList<Planet> list = new ArrayList<Planet>();
        for (Planet planet: planetSet) {
            list.add(planet);
        }
        return list;
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        String message;
        message = "Solar System Name: " + name;
        message = message + "\nSolar System Position: (" + x + "," + y + ")";
        message = message + "\nPlanets:";
        List<Planet> list = getPlanets();
        for (Planet planet: list){
            message = message + "\n\t" + planet.toString();
            message = message + "\n--------------------------------------";
        }
        
        return message;
    }
    /*
    public static void main(String[] args){
        SolarSystem zeSS = new SolarSystem("Afiq", 5, 5);
        System.out.println(zeSS.toString());
    }   */
}

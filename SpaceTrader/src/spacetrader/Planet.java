/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myuniverse;
import java.util.Random;

/**
 *
 * @author lsmoore
 */
public class Planet {
    private String name;
    private int x, y;
    private int pirateChance;
    private int policeLevel;
    private TechLevel techLevel;
    private Resources resources;
    private GovernmentType govType;
    private SolarSystem solarSystem;
    
    
    public Planet(String name, int x, int y, SolarSystem ss){
        this.name = name;
        this.x = x;
        this.y = y;
        solarSystem = ss;
        
        Random rand = new Random();
        pirateChance = rand.nextInt(100);
        policeLevel = rand.nextInt(10);
        techLevel = TechLevel.values()[rand.nextInt(8)];
        govType = GovernmentType.values()[rand.nextInt(6)];
        resources = Resources.values()[rand.nextInt(13)];
    }
    
    public String toString() {
        String message;
        message = "Planet name: " + name;
        message = message + "\n\tPlanet Position: (" + x + "," + y + ")";
        message = message + "\n\tTech Level: " + techLevel.toString();
        message = message + "\n\tResource Level: " + resources.toString();
        message = message + "\n\tGovernment Type: " + govType.toString();
        message = message + "\n\tPolice Level (1-10): " + policeLevel;
        message = message + "\n\tChance of meeting pirates: " + pirateChance + "%";
        //message = message + "\n\tSolar System: " + solarSystem.getName();
        
        return message;
    }
   
}

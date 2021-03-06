/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader;
import java.util.Random;
import javafx.scene.image.Image;
import java.io.Serializable;

/**
 * This class represents a Planet.  It is instantiated by the Solar System class
 * It contains its name, position, police level, tech level, resources,
 * government type, chance of meeting pirates, and its solar system
 * @author lsmoore
 */
public class Planet implements Serializable {
    private static final long serialVersionUID = 1;
    //CHECKSTYLE: OFF
    private static final int TECH_LEVEL_FOR_SHIPYARD = 4;
    private String name;
    private int x, y;
    private int pirateChance;
    private int policeLevel;
    private TechLevel techLevel;
    private Resources resources;
    private GovernmentType govType;
    private SolarSystem solarSystem;
    private Market market;
    private transient Image pic;
    private double sizeX, sizeY;
    private ShipYard shipYard;
    private Weather weather;
    private Random rand = new Random();

    /**
     * This is the constructor. Solar system passes in the name,
     * coordinates, and what solar system it belongs to.
     * It randomly generates the rest.
     * @param name String of the name
     * @param x two ints for x and y
     * @param y
     * @param ss the solar system
     */
    public Planet(String name, int x, int y, SolarSystem ss) {
        this.name = name;
        this.x = x;
        this.y = y;
        pic = setPlanetPic();
        sizeX = pic.getWidth();
        sizeY = pic.getHeight();
        solarSystem = ss;
        weather = new Weather();

        if (name.equals("Second Earth")) {
            policeLevel = 9;
            pirateChance = 0;
            techLevel = TechLevel.HITECH;
            govType = GovernmentType.CAPITALIST;
            resources = Resources.MINERALRICH;
        } else {
            pirateChance = rand.nextInt(100);
            policeLevel = rand.nextInt(10);
            techLevel = TechLevel.values()[rand.nextInt(8)];
            govType = GovernmentType.values()[rand.nextInt(6)];
            resources = Resources.values()[rand.nextInt(13)];
        }
        market = new Market(techLevel.ordinal(), resources.ordinal(), this);

        //if the ship doesn't have a high enough tech level,
        //the shipYard remains null
        shipYard = null;
        if (techLevel.ordinal() > TECH_LEVEL_FOR_SHIPYARD) {
            int priceAdjuster = 0;
            if (resources == Resources.MINERALRICH) {
                priceAdjuster = -10;
            } else if (resources == Resources.MINERALPOOR) {
                priceAdjuster = 10;
            }
            shipYard = new ShipYard(priceAdjuster, techLevel.ordinal());
        }
    }

    public Image setPlanetPic() {
        int option = rand.nextInt(3);
        switch (option) {
            case 0:
                pic = new Image("/spacetrader/resources/graphics (1).png",
                        63, 61, true, true);
                break;
            case 1:
                pic = new Image("/spacetrader/resources/graphics (2).png",
                        63, 61, true, true);
                break;
            case 2:
                pic = new Image("/spacetrader/resources/graphics (3).png",
                        63, 61, true, true);
                break;
            default:
                pic = new Image("/spacetrader/resources/Planet.png");
                break;
        }
        return pic;
    }

    /**
     * Displays the contents of the solar system.
     * returns a string of the contents
     */
    @Override
    public String toString() {
        String message;
        message = "Planet name: " + name;
        message = message + "\n\tPlanet Position: (" + x + "," + y + ")";
        message = message + "\n\tTech Level: " + techLevel.toString();
        message = message + "\n\tResource Level: " + resources.toString();
        message = message + "\n\tGovernment Type: " + govType.toString();
        message = message + "\n\tPolice Level (1-10): " + policeLevel;
        message = message + "\n\tChance of meeting pirates: "
                + pirateChance + "%";
        return message;
    }
/**
 * Decides whether or not there is a collision.
 * @param px
 * @param py
 * @return boolean
 */
    public boolean isHit(double px, double py) {
        if (px >= (x - (sizeX / 2)) && px <= (x + (sizeX / 2))) {
            if (py >= (y - (sizeY / 2)) && py <= (y + (sizeY / 2))) {
                return true;
            }
        }
        return false;
    }
   /**
    * Gets the location.
    * @return location
    */

    public int[] getLocation(){
        int[] location = new int[2];
        location[0] = x;
        location[1] = y;
        return location;
    }
/**
 * Gets the planet name.
 * @return name
 */
    public String getPlanetName() {
        return name;
    }

/**
 * Gets the market.
 * @return market
 */
    public Market getMarket() {
        return market;
    }
/**
 * Gets the weather of the planet.
 * @return weather
 */

    public Weather getWeather() {
        return weather;
    }

/**
 * Gets the picture of the planet.
 * @return pic
 */
    public Image getPlanetPic() {
        if (pic == null) {
           pic = new Image("/spacetrader/resources/Planet.png");
        }
        return pic;
    }
/**
 * Gets the current solar system
 * @return solarSystem
 */
    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    //this makes it so that when a planet has a certain tech level it will have
    //a ship yard to repair
    /**
     * Sets ship yard.
     */
    public void setShipYard() {

    }
/**
 * Determines if the planet has a shipyard.
 * @return boolean
 */
    public boolean hasShipYard() {
        return shipYard != null;
    }
    /**
     * Gets the ship yard.
     * @return shipYard
     */
    public ShipYard getShipYard() {
        return shipYard;
    }
}

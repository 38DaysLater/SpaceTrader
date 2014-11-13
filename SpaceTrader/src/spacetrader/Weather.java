package spacetrader;

import java.io.Serializable;
import java.util.Random;

/**
 *  This class represents the weather
 * It contains information about the temperature, precipitation, and sun, etc
 * @author lsmoore
 */
public class Weather implements Serializable {
    private static final long serialVersionUID = 1;
    private int temperature;
    private int chanceOfPrecipitation;
    private final String[] conditions = {"Rainy", "Sunny", "Cloudy", "Snowy",
                                           "Overcast", "Frightful"};
    private String currentCondition;
    private int humidity;
    private int time;
    private Random rand;

    /**
    * This is the constructor. It current condition on the planet
    */

    public Weather() {
        rand = new Random();
        updateWeather();
        time = 0;

    }

    /**
     * This get method returns the current temperature in String format.
     * @return The temperature as a String
     */

    public String getTemp() {
        if ((time % 4) == 0) {
            updateWeather();
        }
        time++;
        return temperature + " \u2109";
    }

    /**
     * This get method returns the current Humidity in String format.
     * @return The humidity as a String
     */

    public String getHumidity() {
        if ((time % 4) == 0) {
            updateWeather();
        }
        time++;
        return humidity + "%";
    }

    /**
     * This get method returns the current chance of rain in String format.
     * @return The chance of precipitation as a String
     */

    public String getChanceOfPrecipitation() {
        if ((time % 4) == 0) {
            updateWeather();
        }
        time++;
        return chanceOfPrecipitation + "%";
    }

    /**
     * This get method returns the current condition on the planet.
     * @return The general condition of the planet as String
     */

    public String getCurrentCondition() {
        if ((time % 4) == 0) {
            updateWeather();
        }
        time++;
        return currentCondition;
    }

    /**
     * This private method updates the weather on the planet.
     * It is called when time is a multiple of 4, which happens after one cycle
     * of retrieving the weather information.
     */

    private void updateWeather() {
        temperature = rand.nextInt(100);
        chanceOfPrecipitation = rand.nextInt(100);
        humidity = rand.nextInt(100);
        currentCondition = conditions[rand.nextInt(conditions.length)];
    }
}

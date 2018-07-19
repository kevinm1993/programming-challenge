package de.exxcellent.challenge;

import de.exxcellent.challenge.model.WeatherData;
import de.exxcellent.challenge.reader.DataReader;
import de.exxcellent.challenge.reader.WeatherDataReader;

import java.util.Collection;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

    public static void main(String... args) {

        // Your preparation code …
        DataReader<WeatherData> weatherReader = new WeatherDataReader("de/exxcellent/challenge/weather.csv");
        Collection<WeatherData> weatherData = weatherReader.readData();


        String dayWithSmallestTempSpread = "Someday";     // Your day analysis function call …
        String teamWithSmallesGoalSpread = "A good team"; // Your goal analysis function call …

        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
        System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallesGoalSpread);
    }
}

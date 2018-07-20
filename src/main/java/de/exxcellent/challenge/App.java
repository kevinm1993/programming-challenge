package de.exxcellent.challenge;

import de.exxcellent.challenge.model.WeatherData;
import de.exxcellent.challenge.processor.DataProcessor;
import de.exxcellent.challenge.processor.WeatherSpreadProcessor;
import de.exxcellent.challenge.reader.DataReader;
import de.exxcellent.challenge.reader.WeatherDataReader;
import de.exxcellent.challenge.util.logging.Logger;
import de.exxcellent.challenge.util.logging.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String... args) {

        DataReader<WeatherData> weatherReader = new WeatherDataReader("de/exxcellent/challenge/weather.csv");
        Collection<WeatherData> weatherData = weatherReader.readData();
        DataProcessor<WeatherData> weatherSpreadProcessor = new WeatherSpreadProcessor();
        Optional<WeatherData> smallestSpread = weatherSpreadProcessor.process(weatherData);
        if (smallestSpread.isPresent()) {
            LOG.info(String.format("Day with smallest temperature spread: %s", smallestSpread.get().getDay()));
        } else {
            LOG.warn("Could not find any result for weather");
        }
    }
}

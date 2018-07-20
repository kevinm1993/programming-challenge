package de.exxcellent.challenge.processor;

import de.exxcellent.challenge.model.WeatherData;

import java.util.Collection;
import java.util.Optional;

/**
 * Data processor for weather data.
 * <p>
 * It searches the entry with the smallest spread between maximum and minimum temperature.
 */
public class WeatherSpreadProcessor implements DataProcessor<WeatherData> {

    @Override
    public Optional<WeatherData> process(Collection<WeatherData> dataList) {
        int spread = Integer.MAX_VALUE;
        WeatherData smallestSpread = null;
        for (WeatherData weatherData : dataList) {
            int currentSpread = weatherData.getMaximumTemp() - weatherData.getMinimumTemp();
            if (currentSpread < spread) {
                spread = currentSpread;
                smallestSpread = weatherData;
            }
        }
        return (smallestSpread != null) ? Optional.of(smallestSpread) : Optional.empty();
    }
}

package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.WeatherData;

/**
 * Data reader for weather data. This class reads a CSV file with weather data and returns the result as a collection.
 */
public class WeatherDataReader extends AbstractCSVReader<WeatherData> {

    /**
     * Creates an instance for reading weather data from a file resource, which has to be accessible from the classpath.
     *
     * @param resourcePath The path to the weather data CSV file.
     */
    public WeatherDataReader(String resourcePath) {
        super(resourcePath);
    }

    protected WeatherData constructObject(String[] columns, int lineNumber) {
        if (columns.length != 14) {
            final String msg = String.format("Got %d columns in line %d, but expected 14.", columns.length, lineNumber);
            LOG.error(msg);
            throw new DataReaderException(msg);
        }

        WeatherData weatherData = new WeatherData();
        weatherData.setDay(Integer.parseInt(columns[0]));
        weatherData.setMaximumTemp(Integer.parseInt(columns[1]));
        weatherData.setMinimumTemp(Integer.parseInt(columns[2]));
        return weatherData;
    }
}

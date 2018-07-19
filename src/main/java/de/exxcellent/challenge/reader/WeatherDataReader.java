package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.WeatherData;
import de.exxcellent.challenge.util.logging.Logger;
import de.exxcellent.challenge.util.logging.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Data reader for weather data. This class reads a CSV file with weather data and returns the result as a collection.
 */
public class WeatherDataReader implements DataReader<WeatherData> {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherDataReader.class);
    private final String resourcePath;

    /**
     * Creates an instance for reading weather data from a file resource, which has to be accessible from the classpath.
     *
     * @param resourcePath The path to the weather data CSV file.
     */
    public WeatherDataReader(String resourcePath) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("ResourcePath must not be null");
        }
        this.resourcePath = resourcePath;
    }

    @Override
    public Collection<WeatherData> readData() {
        Collection<WeatherData> result = new ArrayList<>();
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(openStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                final int lineNumber = reader.getLineNumber();
                if (lineNumber == 1) {
                    // Skip first line with colunmn headers. We don't verify the order of the headers entries with the expected format,
                    // because it is very unlikely that this order will change.
                    continue;
                }

                // Parse data.
                String[] columns = line.split(",");
                try {
                    WeatherData weatherData = constructObject(columns, lineNumber);
                    result.add(weatherData);
                } catch (RuntimeException e) {
                    // Catch all kinds of runtime exceptions from the parsing code.
                    final String msg = String.format("Exception while parsing line %d: %s", lineNumber, e.toString());
                    LOG.error(msg);
                    throw new DataReaderException(msg);
                }
            }
        } catch (IOException ioe) {
            final String msg = "IOException while reading file: " + ioe;
            LOG.error(msg);
            throw new DataReaderException(msg);
        }
        return result;
    }

    private WeatherData constructObject(String[] columns, int lineNumber) {
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

    private InputStream openStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new DataReaderException("Could not find resource '" + resourcePath + "' in classpath");
        }
        return inputStream;
    }

}

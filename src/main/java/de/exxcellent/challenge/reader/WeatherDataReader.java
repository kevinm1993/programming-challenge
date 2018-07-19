package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.WeatherData;

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
                // TODO implement parsing
            }
        } catch (IOException ioe) {
            throw new DataReaderException("IOException while reading file: " + ioe);
        }
        return result;
    }

    private InputStream openStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new DataReaderException("Could not find resource '" + resourcePath + "' in classpath");
        }
        return inputStream;
    }

}

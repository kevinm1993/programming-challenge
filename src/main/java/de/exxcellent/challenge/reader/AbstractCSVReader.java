package de.exxcellent.challenge.reader;


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
 * Abstract base implementation of {@link DataReader}.
 * <p>
 * This class takes care of reading the CSV data and splits each line into an array of strings. Subclasses have
 * to implement {@link #constructObject(String[], int)}, which should construct a data entity from the given
 * array of strings.
 */
abstract class AbstractCSVReader<T> implements DataReader<T> {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    private final String resourcePath;

    protected AbstractCSVReader(String resourcePath) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("ResourcePath must not be null");
        }
        this.resourcePath = resourcePath;
    }

    @Override
    public Collection<T> readData() {
        Collection<T> result = new ArrayList<>();
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
                    T t = constructObject(columns, lineNumber);
                    result.add(t);
                } catch (RuntimeException e) {
                    // Catch all kinds of runtime exceptions from the parsing code.
                    final String msg = String.format("Exception while parsing line %d: %s", lineNumber, e.toString());
                    LOG.error(msg);
                    throw new DataReaderException(msg);
                }
            }
            return result;
        } catch (IOException ioe) {
            final String msg = "IOException while reading file: " + ioe;
            LOG.error(msg);
            throw new DataReaderException(msg);
        }
    }

    private InputStream openStream() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new DataReaderException("Could not find resource '" + resourcePath + "' in classpath");
        }
        return inputStream;
    }

    /**
     * This method gets called for each line of the CSV input. The columns are split into an array of strings.
     *
     * @param columns    The columns of the current line in the CSV file.
     * @param lineNumber The current line number in the file. Can be used for error messages.
     * @return A data entity.
     */
    protected abstract T constructObject(String[] columns, int lineNumber);
}

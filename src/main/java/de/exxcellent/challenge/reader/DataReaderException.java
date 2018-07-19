package de.exxcellent.challenge.reader;

/**
 * This exception indicates that a {@link DataReader} could not read the requested data.
 */
public class DataReaderException extends RuntimeException {

    /**
     * Constructs a {@code DataReaderException} with the specified detail message.
     *
     * @param message The detail message for the exception.
     */
    public DataReaderException(String message) {
        super(message);
    }
}

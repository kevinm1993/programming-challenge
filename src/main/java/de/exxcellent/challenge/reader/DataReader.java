package de.exxcellent.challenge.reader;

import java.util.Collection;

/**
 * Generic interface for reading data into a collection of entities.
 */
public interface DataReader<T> {

    /**
     * Reads data into a collection of items.
     *
     * @return A collection with data entries. This method never returns {@code null}.
     */
    Collection<T> readData();
}

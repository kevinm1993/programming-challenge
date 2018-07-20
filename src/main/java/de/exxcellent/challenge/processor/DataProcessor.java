package de.exxcellent.challenge.processor;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for a generic data processor.
 * <p>
 * It processes a collection of elements and optionally returns one element satisfying a specific criteria. The criteria is
 * implementation specific.
 */
public interface DataProcessor<T> {

    /**
     * Processes the collection and optionally returns an element.
     *
     * @param dataList The collection with the elements.
     * @return The resulting element as an {@code Optional}.
     */
    Optional<T> process(Collection<T> dataList);
}

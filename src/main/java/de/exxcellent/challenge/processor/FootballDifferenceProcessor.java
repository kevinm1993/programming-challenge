package de.exxcellent.challenge.processor;

import de.exxcellent.challenge.model.FootballData;

import java.util.Collection;
import java.util.Optional;

/**
 * Data processor for football data.
 * <p>
 * It searches the entry with the smallest difference between goals scored and goals received.
 */
public class FootballDifferenceProcessor implements DataProcessor<FootballData> {

    @Override
    public Optional<FootballData> process(Collection<FootballData> dataList) {
        int difference = Integer.MAX_VALUE;
        FootballData smallestDifference = null;
        for (FootballData footballData : dataList) {
            int currentDifference = Math.abs(footballData.getGoalsScored() - footballData.getGoalsReceived());
            if (currentDifference < difference) {
                difference = currentDifference;
                smallestDifference = footballData;
            }
        }
        return (smallestDifference != null) ? Optional.of(smallestDifference) : Optional.empty();
    }
}

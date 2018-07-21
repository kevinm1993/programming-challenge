package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.FootballData;

/**
 * Data reader for football data. This class reads a CSV file with football data and returns the result as a collection.
 */
public class FootballDataReader extends AbstractCSVReader<FootballData> {

    /**
     * Creates an instance for reading football data from a file resource, which has to be accessible from the classpath.
     *
     * @param resourcePath The path to the football data CSV file.
     */
    public FootballDataReader(String resourcePath) {
        super(resourcePath);
    }

    @Override
    protected FootballData constructObject(String[] columns, int lineNumber) {
        if (columns.length != 8) {
            final String msg = String.format("Got %d columns in line %d, but expected 8.", columns.length, lineNumber);
            LOG.error(msg);
            throw new DataReaderException(msg);
        }

        FootballData footballData = new FootballData();
        footballData.setTeamName(columns[0]);
        footballData.setGoalsScored(Integer.parseInt(columns[5]));
        footballData.setGoalsReceived(Integer.parseInt(columns[6]));
        return footballData;
    }
}

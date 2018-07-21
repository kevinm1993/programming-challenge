package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.FootballData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

public class FootballDataReaderTest {

    @Test
    public void testInvalidResource() {
        try {
            new FootballDataReader(null);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            // expected
        }


        DataReader<FootballData> reader = new FootballDataReader("invalid/path.csv");
        try {
            reader.readData();
            Assert.fail("Expected ReaderException");
        } catch (DataReaderException re) {
            // expected
        }
    }

    @Test
    public void testParsing() {
        DataReader<FootballData> reader = new FootballDataReader("de/exxcellent/challenge/reader/football-test.csv");
        Collection<FootballData> footballDataCollection = reader.readData();

        Assert.assertEquals("Invalid collection size", 2, footballDataCollection.size());

        Iterator<FootballData> it = footballDataCollection.iterator();
        FootballData lineOne = it.next();
        Assert.assertEquals("Found invalid value in line 1 in column 'Team'", "Arsenal", lineOne.getTeamName());
        Assert.assertEquals("Found invalid value in line 1 in column 'Goals'", 79, lineOne.getGoalsScored());
        Assert.assertEquals("Found invalid value in line 1 in column 'Goals Allowed'", 36, lineOne.getGoalsReceived());

        FootballData lineTwo = it.next();
        Assert.assertEquals("Found invalid value in line 2 in column 'Team'", "Liverpool", lineTwo.getTeamName());
        Assert.assertEquals("Found invalid value in line 2 in column 'Goals'", 67, lineTwo.getGoalsScored());
        Assert.assertEquals("Found invalid value in line 2 in column 'Goals Allowed'", 30, lineTwo.getGoalsReceived());
    }

    @Test
    public void restInvalidColumns() {
        DataReader<FootballData> reader = new FootballDataReader("de/exxcellent/challenge/reader/football-test-invalid-columns.csv");
        try {
            reader.readData();
            Assert.fail("Expected invalid column count to raise an exception");
        } catch (DataReaderException e) {
            // expected
        }
    }

    @Test
    public void restInvalidData() {
        DataReader<FootballData> reader = new FootballDataReader("de/exxcellent/challenge/reader/football-test-invalid-data.csv");
        try {
            reader.readData();
            Assert.fail("Expected invalid data in lines to raise an exception");
        } catch (DataReaderException e) {
            // expected
        }
    }
}

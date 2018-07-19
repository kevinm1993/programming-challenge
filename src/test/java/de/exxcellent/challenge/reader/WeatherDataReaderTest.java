package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.WeatherData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

public class WeatherDataReaderTest {

    @Test
    public void testInvalidResource() {
        try {
            new WeatherDataReader(null);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException iae) {
            // expected
        }


        DataReader<WeatherData> reader = new WeatherDataReader("invalid/path.csv");
        try {
            reader.readData();
            Assert.fail("Expected ReaderException");
        } catch (DataReaderException dre) {
            // expected
        }
    }

    @Test
    public void testParsing() {
        DataReader<WeatherData> reader = new WeatherDataReader("de/exxcellent/challenge/reader/weather-test.csv");
        Collection<WeatherData> weatherDataCollection = reader.readData();

        Assert.assertEquals("Invalid collection size", 2, weatherDataCollection.size());

        Iterator<WeatherData> it = weatherDataCollection.iterator();
        WeatherData lineOne = it.next();
        Assert.assertEquals("Found invalid value in line 1 in column 'day'", 1, lineOne.getDay());
        Assert.assertEquals("Found invalid value in line 1 in column 'MxT'", 88, lineOne.getMaximumTemp());
        Assert.assertEquals("Found invalid value in line 1 in column 'MnT'", 59, lineOne.getMinimumTemp());

        WeatherData lineTwo = it.next();
        Assert.assertEquals("Found invalid value in line 2 in column 'day'", 2, lineTwo.getDay());
        Assert.assertEquals("Found invalid value in line 2 in column 'MxT'", 79, lineTwo.getMaximumTemp());
        Assert.assertEquals("Found invalid value in line 2 in column 'MnT'", 63, lineTwo.getMinimumTemp());
    }

    @Test
    public void testInvalidColumns() {
        DataReader<WeatherData> reader = new WeatherDataReader("de/exxcellent/challenge/reader/weather-test-invalid-columns.csv");
        try {
            reader.readData();
            Assert.fail("Expected invalid column count to raise an exception");
        } catch (DataReaderException e) {
            // expected
        }
    }

    @Test
    public void testInvalidData() {
        DataReader<WeatherData> reader = new WeatherDataReader("de/exxcellent/challenge/reader/weather-test-invalid-data.csv");
        try {
            reader.readData();
            Assert.fail("Expected invalid data in lines to raise an exception");
        } catch (DataReaderException e) {
            // expected
        }
    }

}

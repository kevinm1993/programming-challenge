package de.exxcellent.challenge.reader;

import de.exxcellent.challenge.model.WeatherData;
import org.junit.Assert;
import org.junit.Test;

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

}

package de.exxcellent.challenge.processor;

import de.exxcellent.challenge.model.WeatherData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class WeatherSpreadProcessorTest {

    @Test
    public void testEmptyCollection() {
        DataProcessor<WeatherData> processor = new WeatherSpreadProcessor();
        Optional<WeatherData> result = processor.process(Collections.emptyList());
        Assert.assertNotNull("Invalid return value for empty collection", result);
        Assert.assertFalse("Optional should be empty", result.isPresent());
    }

    @Test
    public void testProcessing() {
        // spread = 15
        WeatherData data1 = new WeatherData();
        data1.setDay(1);
        data1.setMinimumTemp(10);
        data1.setMaximumTemp(25);

        // spread = 5
        WeatherData data2 = new WeatherData();
        data2.setDay(2);
        data2.setMinimumTemp(15);
        data2.setMaximumTemp(20);

        // spread = 20
        WeatherData data3 = new WeatherData();
        data3.setDay(3);
        data3.setMinimumTemp(10);
        data3.setMaximumTemp(30);

        DataProcessor<WeatherData> processor = new WeatherSpreadProcessor();
        Optional<WeatherData> result = processor.process(Arrays.asList(data1, data2, data3));
        Assert.assertTrue("Optional should not be empty", result.isPresent());
        Assert.assertEquals("Wrong result for day", 2, result.get().getDay());
    }

}

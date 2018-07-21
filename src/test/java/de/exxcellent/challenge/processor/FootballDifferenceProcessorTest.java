package de.exxcellent.challenge.processor;

import de.exxcellent.challenge.model.FootballData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public class FootballDifferenceProcessorTest {

    @Test
    public void testEmptyCollection() {
        DataProcessor<FootballData> processor = new FootballDifferenceProcessor();
        Optional<FootballData> result = processor.process(Collections.emptyList());
        Assert.assertNotNull("Invalid return value for empty collection", result);
        Assert.assertFalse("Optional should be empty", result.isPresent());
    }

    @Test
    public void testProcessing() {
        // difference = 10
        FootballData data1 = new FootballData();
        data1.setTeamName("team1");
        data1.setGoalsScored(10);
        data1.setGoalsReceived(20);

        // difference = -3
        FootballData data2 = new FootballData();
        data2.setTeamName("team2");
        data2.setGoalsScored(25);
        data2.setGoalsReceived(22);

        // difference = 20
        FootballData data3 = new FootballData();
        data3.setTeamName("team3");
        data3.setGoalsScored(15);
        data3.setGoalsReceived(35);

        DataProcessor<FootballData> processor = new FootballDifferenceProcessor();
        Optional<FootballData> result = processor.process(Arrays.asList(data1, data2, data3));
        Assert.assertTrue("Optional should not be empty", result.isPresent());
        Assert.assertEquals("Wrong result for team", "team2", result.get().getTeamName());
    }

}

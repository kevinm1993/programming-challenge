package de.exxcellent.challenge.model;

/**
 * Class for holding weather data.
 */
public class WeatherData {
    private int day;
    private int maximumTemp;
    private int minimumTemp;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(int maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public int getMinimumTemp() {
        return minimumTemp;
    }

    public void setMinimumTemp(int minimumTemp) {
        this.minimumTemp = minimumTemp;
    }
}

package com.example.betterweather;

public class FutureWeather {
    private final String temperature;
    private final String day;
    private final int imageResource;

    public FutureWeather(String temperature, String day, int imageResource) {
        this.temperature = temperature;
        this.day = day;
        this.imageResource = imageResource;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getDay() {
        return day;
    }

    public int getImageResource() {
        return imageResource;
    }
}

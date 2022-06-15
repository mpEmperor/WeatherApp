package com.example.fse;

import java.util.ArrayList;

public class Forecast {
    private ArrayList<ForecastDay> forecastday;

    public float getAverage() {
        int sum = 0;
        for (int i = 0; i < forecastday.size(); i++) {
            sum += forecastday.get(i).getDay().getAvgtemp_c();
        }
        return sum / forecastday.size();
    }
    public ArrayList<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}

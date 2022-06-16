package com.example.fse;

import java.util.ArrayList;

public class Forecast {
    private ArrayList<ForecastDay> forecastday;

    public float getAverageCelsius() {
        float sum = 0;
        for (ForecastDay forecastDay : forecastday) {
            sum += forecastDay.getDay().getAvgtemp_c();
        }
        return sum / forecastday.size();
    }
    public float getAverageFahrenheit() {
        float sum = 0;
        for (ForecastDay forecastDay : forecastday) {
            sum += forecastDay.getDay().getAvgtemp_f();
        }
        return sum / forecastday.size();
    }
    public ArrayList<Float> precipTrendMM() {
        ArrayList<Float> precipData = new ArrayList<Float>();
        for (int i = 0; i < forecastday.size(); i ++) {
            for (int j = 0; j < forecastday.get(i).getHour().size(); j ++) {
                precipData.add(forecastday.get(i).getHour().get(j).getPrecip_mm());
            }
        }
        return precipData;
    }
    public ArrayList<Float> precipTrendIN() {
        ArrayList<Float> precipData = new ArrayList<Float>();
        for (int i = 0; i < forecastday.size(); i ++) {
            for (int j = 0; j < forecastday.get(i).getHour().size(); i ++) {
                precipData.add(forecastday.get(i).getHour().get(j).getPrecip_in());
            }
        }
        return precipData;
    }
    public ArrayList<Float> tempTrendC() {
        ArrayList<Float> tempData = new ArrayList<Float>();
        for (int i = 0; i < forecastday.size(); i ++) {
            for (int j = 0; j < forecastday.get(i).getHour().size(); i ++) {
                tempData.add(forecastday.get(i).getHour().get(j).getTemp_c());
            }
        }
        return tempData;
    }
    public ArrayList<Float> tempTrendF() {
        ArrayList<Float> tempData = new ArrayList<Float>();
        for (int i = 0; i < forecastday.size(); i ++) {
            for (int j = 0; j < forecastday.get(i).getHour().size(); i ++) {
                tempData.add(forecastday.get(i).getHour().get(j).getTemp_f());
            }
        }
        return tempData;
    }
    public ArrayList<ForecastDay> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<ForecastDay> forecastday) {
        this.forecastday = forecastday;
    }
}

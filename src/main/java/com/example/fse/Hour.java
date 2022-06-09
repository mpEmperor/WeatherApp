package com.example.fse;

public class Hour extends Current {
    private int time_epoch;
    private String time;
    private float windchill_c;
    private float windchill_f;
    private float heatindex_c;
    private float heatindex_f;
    private float dewpoint_c;
    private float dewpoint_f;

    public int getTime_epoch() {
        return time_epoch;
    }

    public void setTime_epoch(int time_epoch) {
        this.time_epoch = time_epoch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getWindchill_c() {
        return windchill_c;
    }

    public void setWindchill_c(float windchill_c) {
        this.windchill_c = windchill_c;
    }

    public float getWindchill_f() {
        return windchill_f;
    }

    public void setWindchill_f(float windchill_f) {
        this.windchill_f = windchill_f;
    }

    public float getHeatindex_c() {
        return heatindex_c;
    }

    public void setHeatindex_c(float heatindex_c) {
        this.heatindex_c = heatindex_c;
    }

    public float getHeatindex_f() {
        return heatindex_f;
    }

    public void setHeatindex_f(float heatindex_f) {
        this.heatindex_f = heatindex_f;
    }

    public float getDewpoint_c() {
        return dewpoint_c;
    }

    public void setDewpoint_c(float dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }

    public float getDewpoint_f() {
        return dewpoint_f;
    }

    public void setDewpoint_f(float dewpoint_f) {
        this.dewpoint_f = dewpoint_f;
    }
}

package com.example.daniel.accesoadatos_xml.Ej2;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by daniel on 9/12/16.
 */

public class Prediction {
    private Calendar day;
    private Map<String, String> skyStates;
    private int tempMax;
    private int tempMin;

    public static final String SKYSTATE_00_24 = "00-24";
    public static final String SKYSTATE_00_12 = "00-12";
    public static final String SKYSTATE_12_24 = "12-24";
    public static final String SKYSTATE_00_06 = "00-06";
    public static final String SKYSTATE_06_12 = "06-12";
    public static final String SKYSTATE_12_18 = "12-18";
    public static final String SKYSTATE_18_24 = "18-24";

    public Prediction(){
        skyStates = new TreeMap<String, String>();
    }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public Map<String, String> getSkyStates() {
        return skyStates;
    }

    public void setSkyStates(Map<String, String> skyStates) {
        this.skyStates = skyStates;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }
}

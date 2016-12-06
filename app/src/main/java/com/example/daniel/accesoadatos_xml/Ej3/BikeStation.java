package com.example.daniel.accesoadatos_xml.Ej3;

import java.util.Calendar;

/**
 * Created by daniel on 6/12/16.
 */

public class BikeStation {

    private String title;
    private String state;
    private int bikesAvailable;
    private int anchorsAvailable;
    private Calendar lastUpdated;
    private String mapUri;
    private String coordinates;

    public BikeStation(){}

    public BikeStation(String title, String state, int bikesAvailable, int anchorsAvailable, Calendar lastUpdated, String mapUri, String coordinates) {
        this.title = title;
        this.state = state;
        this.bikesAvailable = bikesAvailable;
        this.anchorsAvailable = anchorsAvailable;
        this.lastUpdated = lastUpdated;
        this.mapUri = mapUri;
        this.coordinates = coordinates;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getBikesAvailable() {
        return bikesAvailable;
    }

    public void setBikesAvailable(int bikesAvailable) {
        this.bikesAvailable = bikesAvailable;
    }

    public int getAnchorsAvailable() {
        return anchorsAvailable;
    }

    public void setAnchorsAvailable(int anchorsAvailable) {
        this.anchorsAvailable = anchorsAvailable;
    }

    public Calendar getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getMapUri() {
        return mapUri;
    }

    public void setMapUri(String mapUri) {
        this.mapUri = mapUri;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}

package com.bdjobs.training.firebasetest;

/**
 * Created by FIROZ HASAN on 3/12/2018.
 */

public class LatLong {
    int longitude, latitude;

    public LatLong(Integer longitude, Integer latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }
}

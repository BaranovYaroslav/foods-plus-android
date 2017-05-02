package com.example.app.util;

import android.location.Location;
import android.location.LocationManager;

/**
 * Created by Ярослав on 03.05.2017.
 */
public class LocationExtractor {

    public static double getLatitude(LocationManager locationManager) throws NullPointerException{
        double result;
        try {
            result = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();
        } catch (Exception e) {
            return CafeSearchStrategy.DEFAULT_COORDINATE;
        }
        return result;
    }

    public static double getLongitude(LocationManager locationManager) {
        double result;
        try {
            result = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
        } catch (Exception e) {
            return CafeSearchStrategy.DEFAULT_COORDINATE;
        }
        return result;
    }
}

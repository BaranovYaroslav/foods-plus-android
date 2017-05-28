package com.example.app.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Ярослав on 03.05.2017.
 */
public class LocationExtractor extends Activity {

    public double getLatitude(LocationManager locationManager) throws NullPointerException {
        double result = -1;
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                result = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude();

            }
        } catch (Exception e) {
            return CafeSearchStrategy.DEFAULT_COORDINATE;
        }
        return result;
    }

    public double getLongitude(LocationManager locationManager) {
        double result = -1;
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                result = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude();
            }

        } catch (Exception e) {
            return CafeSearchStrategy.DEFAULT_COORDINATE;
        }
        return result;
    }
}

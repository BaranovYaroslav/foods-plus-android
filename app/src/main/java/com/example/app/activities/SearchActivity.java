package com.example.app.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.example.app.R;
import com.example.app.data.Cafe;
import com.example.app.data.ModelConstants;
import com.example.app.data.db.DbHandler;
import com.example.app.data.db.IDbHandler;
import com.example.app.util.CafeSearchStrategy;
import com.example.app.util.LocationExtractor;
import com.example.app.util.SimpleCafeSearchStrategy;

import java.util.ArrayList;

/**
 * Created by Ярослав on 06.04.2017.
 **/
public class SearchActivity extends Activity {

    private LocationManager locationManager;

    private CafeSearchStrategy cafeSearchStrategy;

    private IDbHandler dbHandler;

    private EditText minSum;
    private EditText maxSum;
    private RadioButton isMeat;
    private RadioButton isVegetarian;
    private RadioButton isCakes;
    private Switch considerUserLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        initializeSearchComponents();

        cafeSearchStrategy = new SimpleCafeSearchStrategy();
        dbHandler = new DbHandler(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }


    private void initializeSearchComponents() {
        minSum = (EditText) findViewById(R.id.minSum);
        maxSum = (EditText) findViewById(R.id.maxSum);
        isMeat = (RadioButton) findViewById(R.id.isMeat);
        isVegetarian = (RadioButton) findViewById(R.id.isVegetarian);
        isCakes = (RadioButton) findViewById(R.id.isCakes);
        considerUserLocation = (Switch) findViewById(R.id.considerUserLocation);
    }

    public void goToResult(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("cafes", getResult());

            startActivity(intent);
        }

    }

    private ArrayList<Cafe> getResult() {
        cafeSearchStrategy.loadCafes(dbHandler.getAllWithCheckFromRemoteDb());
        return cafeSearchStrategy.search(getMinSum(), getMaxSum(), getType(),
                                         considerUserLocation.isChecked(),
                                         LocationExtractor.getLatitude(locationManager),
                                         LocationExtractor.getLongitude(locationManager));
    }

    private double getMinSum(){
        Double min;
        try {
            min = Double.parseDouble(minSum.getText().toString());
        } catch (NumberFormatException e) {
            min = CafeSearchStrategy.DEFAULT_MIN_COST;
        }
        return min;
    }

    private double getMaxSum() {
        Double max;
        try {
            max = Double.parseDouble(maxSum.getText().toString());
        } catch (NumberFormatException e) {
            max = CafeSearchStrategy.DEFAULT_MAX_COST;
        }
        return max;
    }

    private String getType() {
        String type = ModelConstants.DEFAULT_TYPE;

        if(isMeat.isChecked()) {
            type = ModelConstants.MEAT_TYPE;
        }
        if(isVegetarian.isChecked()) {
            type = ModelConstants.VEGETARIAN_TYPE;
        }
        if(isCakes.isChecked()) {
            type = ModelConstants.CAKES_TYPE;
        }

        return type;
    }
}

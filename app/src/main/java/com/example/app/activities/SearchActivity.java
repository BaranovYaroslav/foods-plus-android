package com.example.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.example.app.R;
import com.example.app.data.Cafe;
import com.example.app.data.CafeMock;
import com.example.app.data.ModelConstants;
import com.example.app.util.CafeSearchStrategy;
import com.example.app.util.SimpleCafeSearchStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярослав on 06.04.2017.
 **/
public class SearchActivity extends Activity {

    private CafeSearchStrategy cafeSearchStrategy;

    private List<Cafe> result;

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

        cafeSearchStrategy = new SimpleCafeSearchStrategy();

        initializeSearchComponents();
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
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("cafes", getResult());
        startActivity(intent);
    }

    private ArrayList<Cafe> getResult() {
        cafeSearchStrategy.loadCafes(CafeMock.getCafes());
        return cafeSearchStrategy.search(getMinSum(), getMaxSum(), getType(), 0, 0);
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

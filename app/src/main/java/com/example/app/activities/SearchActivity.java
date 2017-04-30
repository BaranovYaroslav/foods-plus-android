package com.example.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import com.example.app.R;
import com.example.app.data.Cafe;
import com.example.app.util.SearchStrategy;

import java.util.List;

/**
 * Created by Ярослав on 06.04.2017.
 **/
public class SearchActivity extends Activity {

    private SearchStrategy searchStrategy;

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
        intent.putExtra("info", constructResult());
        startActivity(intent);
    }

    private String constructResult() {
        return "Result: " + minSum.getText() + " " + maxSum.getText() + " "
                        + isMeat.isChecked() + " " + isVegetarian.isChecked() + " "
                        + isCakes.isChecked() + " " + considerUserLocation.isChecked();
    }
}

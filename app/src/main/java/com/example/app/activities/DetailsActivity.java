package com.example.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.data.Cafe;

/**
 * Created by Ярослав on 09.04.2017.
 */
public class DetailsActivity extends Activity {

    private TextView cafeName;
    private TextView description;
    private TextView middleCost;
    private TextView address;

    private double x;
    private double y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        initializeViewComponents();
        addCafeToLayout((Cafe) getIntent().getSerializableExtra("cafe"));
    }

    public void addCafeToLayout(Cafe cafe) {
        cafeName.setText(cafe.getName());
        description.append(cafe.getDescription());
        middleCost.append(String.valueOf(cafe.getMiddleCost()));
        address.append(cafe.getAddress());

        x = cafe.getX();
        y = cafe.getY();
    }

    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("x", x);
        intent.putExtra("y", y);
        startActivity(intent);
    }

    private void initializeViewComponents() {
        cafeName = (TextView) findViewById(R.id.cafeName);
        description = (TextView) findViewById(R.id.description);
        middleCost = (TextView) findViewById(R.id.middleCost);
        address = (TextView) findViewById(R.id.address);
    }
}

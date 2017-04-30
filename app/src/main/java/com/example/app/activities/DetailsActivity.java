package com.example.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.data.Cafe;

/**
 * Created by Ярослав on 09.04.2017.
 */
public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);

        addCafeToLayout((Cafe) getIntent().getSerializableExtra("cafe"));
    }

    public void addCafeToLayout(Cafe cafe) {
        Toast.makeText(getApplicationContext(), cafe.getDescription(), Toast.LENGTH_LONG).show();
    }

    public void showMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}

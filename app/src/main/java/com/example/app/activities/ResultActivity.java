package com.example.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.data.Cafe;
import com.example.app.data.CafeMock;

import java.util.List;

/**
 * Created by Ярослав on 06.04.2017.
 */
public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.result_layout, null);

        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView);

            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.cafes, null));

            TextView info = new TextView(this);
            String infoString = getIntent().getStringExtra("info");
            info.setText(infoString);
            ll.addView(info);

            final List<Cafe> cafes = CafeMock.getCages();
            for (int i = 0; i < cafes.size(); i++) {
                LinearLayout cofee = new LinearLayout(this);
                cofee.setMinimumHeight(300);
                cofee.setBackgroundColor(0xFFFFFFFF);

                GradientDrawable border = new GradientDrawable();
                border.setColor(0xFFFFFFFF);
                border.setStroke(3, 0xFF000000); //black border with full opacity
                border.setCornerRadius(20);
                cofee.setDividerDrawable(border);

                cofee.setBackground(border);


                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                lp.setMargins(40, 40, 40, 0);
                cofee.setLayoutParams(lp);

                final Cafe currentCafe = cafes.get(i);

                final TextView name = new TextView(this);
                name.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5.0f,  getResources().getDisplayMetrics()), 1.0f);
                name.setPadding(10, 10, 10, 10);
                name.setText("Name: " + currentCafe.getName() + "\n"
                            + "Description: " + currentCafe.getDescription() + "\n"
                            + "Middle price: " + currentCafe.getMiddlePrice() + "\n"
                            + "Address: " + currentCafe.getAddress());
                cofee.addView(name);
                LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                np.setMargins(10, 10, 10, 0);
                name.setLayoutParams(np);

                ll.addView(cofee);

                cofee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                        intent.putExtra("cafe", currentCafe);
                        startActivity(intent);
                    }
                });
            }
            scrollView.addView(ll);

        setContentView(view);
    }
}

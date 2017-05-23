package com.example.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.data.Cafe;
import com.example.app.data.ModelConstants;
import com.example.app.data.db.DbHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярослав on 06.04.2017.
 */
public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constructLayout();
    }

    private void constructLayout(){

        List<Cafe> cafes = (ArrayList<Cafe>) getIntent().getSerializableExtra("cafes");

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.result_layout, null);

        ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        scrollView.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.search_background, null));

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < cafes.size(); i++) {
            addCafeToLayout(cafes.get(i), ll);
        }
        scrollView.addView(ll);
        setContentView(view);
    }

    private void addCafeToLayout(final Cafe cafe, LinearLayout ll) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setMinimumHeight(190);
        linearLayout.setBackgroundColor(0xFFFFFFFF);
        linearLayout.setAlpha(0.8F);

        GradientDrawable border = new GradientDrawable();
        border.setColor(0xFFFFFFFF);
        border.setStroke(3, 0xFF000000);
        border.setCornerRadius(10);
        linearLayout.setDividerDrawable(border);

        linearLayout.setBackground(border);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(40, 40, 40, 0);
        linearLayout.setLayoutParams(lp);

        final TextView name = new TextView(this);
        name.setLineSpacing(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5.0f,  getResources().getDisplayMetrics()), 1.0f);
        name.setPadding(10, 10, 10, 10);
        name.setText(String.valueOf("Name: " + cafe.getName() + "\n"
                + "Description: " + cafe.getDescription()));
        linearLayout.addView(name);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        np.setMargins(10, 10, 10, 0);
        name.setLayoutParams(np);

        ll.addView(linearLayout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("cafe", cafe);
                startActivity(intent);
            }
        });
    }
}

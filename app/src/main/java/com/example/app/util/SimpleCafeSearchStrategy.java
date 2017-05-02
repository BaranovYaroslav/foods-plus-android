package com.example.app.util;

import android.app.Activity;

import com.example.app.data.Cafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярослав on 30.04.2017.
 */
public class SimpleCafeSearchStrategy implements CafeSearchStrategy {

    private CafeSetFilter cafeSetFilter;

    public SimpleCafeSearchStrategy() {
        cafeSetFilter = new CafeSetFilter();
    }

    public void loadCafes(List<Cafe> cafes) {
        cafeSetFilter.setCafes(cafes);
    }

    @Override
    public ArrayList<Cafe> search(double min, double max, String type, boolean considerLocation,
                                  double x, double y) {
        cafeSetFilter.filterBySum(min, max)
                     .filterByType(type)
                     .filterByLocation(considerLocation, x, y)
                     .getCafes();
        return new ArrayList<>(cafeSetFilter.getCafes());
    }
}

package com.example.app.util;

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
    public ArrayList<Cafe> search(double min, double max, String type, double x, double y) {
        return new ArrayList<>(cafeSetFilter.filterBySum(min, max)
                                            .filterByType(type)
                                            .filterByLocation(x, y)
                                            .getCafes());
    }
}

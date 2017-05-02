package com.example.app.util;

import com.example.app.data.Cafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ярослав on 30.04.2017.
 */
public interface CafeSearchStrategy {

    double DEFAULT_MIN_COST = -1;
    double DEFAULT_MAX_COST = 1000000;
    double DEFAULT_COORDINATE = 1000;

    void loadCafes(List<Cafe> cafes);

    ArrayList<Cafe> search(double min, double max, String type, boolean considerLocation,
                                  double x, double y);
}


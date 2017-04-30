package com.example.app.util;

import com.example.app.data.Cafe;
import com.example.app.data.ModelConstants;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ярослав on 30.04.2017.
 */
public class CafeSetFilter {

    private List<Cafe> cafes;

    public CafeSetFilter() {
    }

    public List<Cafe> getCafes() {
        return cafes;
    }

    public void setCafes(List<Cafe> cafes) {
        this.cafes = cafes;
    }

    public CafeSetFilter filterBySum(double min, double max) {
        Iterator<Cafe> iterator = cafes.iterator();

        while(iterator.hasNext()) {
            Cafe cafe = iterator.next();
            if((cafe.getMiddleCost() < min) || (cafe.getMiddleCost() > max)) {
                iterator.remove();
            }
        }

        return this;
    }

    public CafeSetFilter filterByType(String type) {
        if(!type.equals(ModelConstants.DEFAULT_TYPE)) {
            Iterator<Cafe> iterator = cafes.iterator();

            while(iterator.hasNext()) {
                Cafe cafe = iterator.next();
                if(!cafe.getType().equals(type)) {
                    iterator.remove();
                }
            }
        }

        return this;
    }

    public CafeSetFilter filterByLocation(double x, double y) {
        return this;
    }
}

package com.example.app.util;

import com.example.app.data.Cafe;
import com.example.app.data.ModelConstants;

import java.util.Arrays;
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

    public CafeSetFilter filterByLocation(boolean considerLocation, double x, double y) {
        if(considerLocation && x != CafeSearchStrategy.DEFAULT_COORDINATE
                            && y != CafeSearchStrategy.DEFAULT_COORDINATE) {
            Cafe[] cafes = this.cafes.toArray(new Cafe[this.cafes.size()]);
            for (int i = 0; i < cafes.length - 1; i++) {
                for (int j = 1; j < cafes.length - i; j++) {
                    if(calculateDistance(x, y, cafes[j - 1]) > calculateDistance(x, y, cafes[j])) {
                        Cafe temp = cafes[j - 1];
                        cafes[j - 1] = cafes[j];
                        cafes[j] = temp;
                    }
                }
            }
            this.cafes = Arrays.asList(cafes);
        }

        return this;
    }

    private double calculateDistance(double x, double y, Cafe cafe) {
        return Math.sqrt(Math.pow(x - cafe.getCoordinates().getX() ,2) +
                         Math.pow(y - cafe.getCoordinates().getY(), 2));
    }
}

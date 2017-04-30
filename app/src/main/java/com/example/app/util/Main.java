package com.example.app.util;

import com.example.app.data.Cafe;
import com.example.app.data.CafeMock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ярослав on 30.04.2017.
 */
public class Main {

    public static void main(String[] args) {
        Iterator<Cafe> iterator = CafeMock.getCafes().iterator();

        while(iterator.hasNext()) {
            Cafe cafe = iterator.next();
            if((cafe.getMiddleCost() < 100) || (cafe.getMiddleCost() > 10)) {
                iterator.remove();
            }
        }

        System.out.println(Arrays.toString(CafeMock.getCafes().toArray()));
    }
}

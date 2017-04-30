package com.example.app.data;

import java.util.ArrayList;
import java.util.List;


public class CafeMock {

    public static List<Cafe> getCafes() {
        ArrayList<Cafe> cafes = new ArrayList<Cafe>();
        for (int i = 0; i < 20; i++) {
            cafes.add(new Cafe("Cafe" + i, "good cafe", i*2, "Address", ModelConstants.DEFAULT_TYPE));
        }

        for (int i = 0; i < 3; i++) {
            cafes.add(new Cafe("Cafe" + i, "good cafe", 20, "Address", ModelConstants.CAKES_TYPE));
        }
        return cafes;
    }

}

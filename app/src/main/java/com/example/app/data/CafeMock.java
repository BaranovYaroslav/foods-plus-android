package com.example.app.data;

import java.util.ArrayList;
import java.util.List;


public class CafeMock {

    private static ArrayList<Cafe> cafes = new ArrayList<Cafe>();

    public static List<Cafe> getCages() {
        for (int i = 0; i < 20; i++) {
            cafes.add(new Cafe("Cafe" + i, "good cafe", i*2, "Address"));
        }

        return cafes;
    }

    public ArrayList<Cafe> getCafes() {
        return cafes;
    }
}

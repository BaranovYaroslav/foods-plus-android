package com.example.app.data;

/**
 * Created by Ярослав on 27.05.2017.
 */
public class Coordinates {

    private double x;

    private double y;

    public Coordinates() {
    }

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

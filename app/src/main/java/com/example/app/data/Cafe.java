package com.example.app.data;

import java.io.Serializable;

public class Cafe implements Serializable{

    private String name;

    private String description;

    private double middleCost;

    private String address;

    private String type;

    private double x;

    private double y;

    public Cafe() {}

    public Cafe(String name, String description, double middleCost, String address, String type) {
        this.name = name;
        this.description = description;
        this.middleCost = middleCost;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMiddleCost() {
        return middleCost;
    }

    public void setMiddleCost(double middleCost) {
        this.middleCost = middleCost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

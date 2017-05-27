package com.example.app.data;

import java.io.Serializable;

public class Cafe implements Serializable{

    private long id;

    private String name;

    private String description;

    private double middleCost;

    private String address;

    private String type;

    private Coordinates coordinates;

    public Cafe() {}

    public Cafe(String name, String description, double middleCost, String address, String type,
                Coordinates coordinates) {
        this.name = name;
        this.description = description;
        this.middleCost = middleCost;
        this.address = address;
        this.type = type;
        this.coordinates = coordinates;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + description + " " + type + " " + middleCost + " "
                  + address + " " + coordinates.getX() + " " + coordinates.getY();
    }
}

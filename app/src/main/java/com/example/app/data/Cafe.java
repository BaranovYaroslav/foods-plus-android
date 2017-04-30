package com.example.app.data;

import java.io.Serializable;

public class Cafe implements Serializable{

    private String name;

    private String description;

    private double middlePrice;

    private String address;

    public Cafe() {}

    public Cafe(String name, String description, double middlePrice, String address) {
        this.name = name;
        this.description = description;
        this.middlePrice = middlePrice;
        this.address = address;
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

    public double getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(double middlePrice) {
        this.middlePrice = middlePrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

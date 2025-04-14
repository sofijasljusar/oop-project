package com.plandiy.model;

public class Resource {
    private String id;
    private String name;
    private String type; //todo - human or material
    private boolean availability;
    private double price;

    private boolean isAvailable() {
        return availability;
    }

    private void reserve() {
        // todo: add check to make sure resource is not taken already
        availability = false;
    }

    private void makeAvailable() {
        availability = true;
    }

}

package com.plandiy.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Resource {
    private String id;
    private String name;
    private ResourceType type;
    private boolean availability;
    private BigDecimal price;

    public Resource(String name, ResourceType type, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.availability = true;
        this.price = price;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void reserve() {
        if (isAvailable()) {
            availability = false;
        }
        else {
            throw new IllegalStateException("Item is already reserved or unavailable.");
        }
    }

    public void makeAvailable() {
        availability = true;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public ResourceType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

package com.plandiy.model.resource;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a resource that can be allocated to a project.
 */
public class Resource {
    private String id;
    private String name;
    private ResourceType type;
    private boolean availability;
    private BigDecimal price;

    /**
     * Constructs a Resource object.
     *
     * @param name  the name of the resource
     * @param type  the type of resource
     * @param price the cost of the resource
     */
    public Resource(String name, ResourceType type, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.availability = true;
        this.price = price;
    }

    /**
     * Checks if the resource is available.
     *
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return availability;
    }

    /**
     * Reserves the resource, making it unavailable.
     *
     * @throws IllegalStateException if already reserved
     */
    public void reserve() {
        if (isAvailable()) {
            availability = false;
        }
        else {
            throw new IllegalStateException("Item is already reserved or unavailable.");
        }
    }

    /**
     * Makes the resource available again.
     */
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

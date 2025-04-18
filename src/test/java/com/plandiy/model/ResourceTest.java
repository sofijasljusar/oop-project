package com.plandiy.model;

import com.plandiy.model.resource.Resource;
import com.plandiy.model.resource.ResourceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    private Resource resource;

    @BeforeEach
    void setUp() {
        resource = new Resource("Projector", ResourceType.MATERIAL, new BigDecimal("150.00"));
    }

    @Test
    void testConstructorInitializesCorrectly() {
        assertEquals("Projector", resource.getName());
        assertEquals(ResourceType.MATERIAL, resource.getType());
        assertEquals(new BigDecimal("150.00"), resource.getPrice());
        assertTrue(resource.isAvailable());
    }

    @Test
    void testReserveSetsAvailabilityToFalse() {
        resource.reserve();
        assertFalse(resource.isAvailable());
    }

    @Test
    void testReserveThrowsExceptionIfAlreadyReserved() {
        resource.reserve();

        IllegalStateException exception = assertThrows(IllegalStateException.class, resource::reserve);
        assertEquals("Item is already reserved or unavailable.", exception.getMessage());
    }

    @Test
    void testMakeAvailableSetsAvailabilityToTrue() {
        resource.reserve(); // first reserve to make it unavailable
        assertFalse(resource.isAvailable());

        resource.makeAvailable(); // then make available again
        assertTrue(resource.isAvailable());
    }
}

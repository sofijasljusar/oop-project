package com.plandiy.model.dao;

import java.util.Map;

/**
 * DAO stands for Data Access Object. Generic interface for basic CRUD operations on entities of type T.
 * Provides methods to create, read, update, delete and fetch all entities.
 *
 * @param <T> The type of the entity.
 */
public interface Dao<T> {
    /**
     * Creates a new entity in the data store.
     *
     * @param entity The entity to be created.
     */
    void create(T entity);

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity.
     * @return The entity with the given identifier.
     */
    T read(String id);

    /**
     * Updates an existing entity in the data store.
     *
     * @param entity The entity to be updated.
     */
    void update(T entity);

    /**
     * Deletes an entity by its unique identifier.
     *
     * @param id The unique identifier of the entity to be deleted.
     */
    void delete(String id);

    /**
     * Retrieves all entities from the data store.
     *
     * @return A map of all entities where the key is the entity's unique identifier.
     */
    Map<String, T> getAll();
}

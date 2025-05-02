package com.plandiy.model.dao;

import java.util.Map;

public interface Dao<T> {
    void create(T entity);
    T read(String id);
    void update(T entity);
    void delete(String id);
    Map<String, T> getAll();
}

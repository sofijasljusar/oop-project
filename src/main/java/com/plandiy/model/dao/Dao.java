package com.plandiy.model.dao;

import java.util.List;

public interface Dao<T, ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
    List<T> getAll();
}

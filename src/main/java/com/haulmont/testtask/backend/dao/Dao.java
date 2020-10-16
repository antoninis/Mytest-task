package com.haulmont.testtask.backend.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    void delete(T object);
    void save(T object);
    void change(T object);

}

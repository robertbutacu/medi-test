package com.medi.test.meditest.services;

import java.util.List;

public interface ICrudService<T> {
    T save(T entity);

    List<T> getAll();

    T getById(Long id);

    void delete(Long id);
}

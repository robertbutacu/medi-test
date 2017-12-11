package com.medi.test.meditest.services.contracts;

import java.util.List;

public interface ICrudService<T> {
    void save(T entity);

    List<T> getAll();

    T getById(Long id);

    void delete(Long id) throws Exception;
}

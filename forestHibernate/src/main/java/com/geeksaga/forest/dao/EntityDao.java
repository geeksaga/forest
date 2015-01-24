package com.geeksaga.forest.dao;

import java.util.List;

import com.geeksaga.forest.repositories.entity.BaseEntity;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface EntityDao<T extends BaseEntity>
{
    List<T> getAll();

    void deleteAll();

    T getById(Long id);

    void add(T entity);

    void update(T entity);

    int countAll();
}
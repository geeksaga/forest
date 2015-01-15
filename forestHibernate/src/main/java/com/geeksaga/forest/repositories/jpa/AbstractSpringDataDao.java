package com.geeksaga.forest.repositories.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.geeksaga.forest.dao.EntityDao;
import com.geeksaga.forest.repositories.entity.BaseEntity;

public abstract class AbstractSpringDataDao<T extends BaseEntity> implements EntityDao<T>
{
    @Autowired
    protected JpaRepository<T, Long> repository;

    @Override
    public List<T> getAll()
    {
        return repository.findAll();
    }

    @Override
    public void deleteAll()
    {
        repository.deleteAll();
    }

    @Override
    public T getById(Long id)
    {
        return repository.findOne(id);
    }

    @Override
    public void add(T entity)
    {
        repository.save(entity);
    }

    @Override
    public void update(T entity)
    {
        repository.save(entity);
    }

    @Override
    public int countAll()
    {
        return Long.valueOf(repository.count()).intValue();
    }
}
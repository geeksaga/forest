package com.geeksaga.forest.dao;

import java.util.List;

import com.geeksaga.forest.repositories.entity.Seed;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface SeedDao extends EntityDao<Seed>
{
    Seed save(Seed seed);
    
    List<Seed> save(Iterable<Seed> list);
    
    Seed findBySid(Long sid);
    
    List<Seed> findTopN(int page, int size);
}
package com.geeksaga.forest.dao;

import java.util.List;

import com.geeksaga.forest.repositories.entity.Seed;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface SeedDao extends EntityDao<Seed>
{
    Seed findBySid(Long sid);
    
    List<Seed> findTop10();
}
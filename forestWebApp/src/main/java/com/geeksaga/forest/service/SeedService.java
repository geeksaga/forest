package com.geeksaga.forest.service;

import java.util.List;

import com.geeksaga.forest.repositories.entity.Seed;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface SeedService
{
    Seed save(Seed seed);

    List<Seed> findTopN(int page, int size);
}
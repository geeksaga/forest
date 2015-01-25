package com.geeksaga.forest.dao;

import com.geeksaga.forest.repositories.entity.TagMap;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface TagMapDao
{
    TagMap save(TagMap tagMap);
    
    boolean exists(TagMap tagMap);
}
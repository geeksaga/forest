package com.geeksaga.forest.dao;

import com.geeksaga.forest.repositories.entity.Tag;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface TagDao extends EntityDao<Tag>
{
    Tag findByTagName(Tag tag);
    
    Tag updateCnt(Tag tag);
}
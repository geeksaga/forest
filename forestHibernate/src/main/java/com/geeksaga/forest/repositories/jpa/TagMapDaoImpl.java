package com.geeksaga.forest.repositories.jpa;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.geeksaga.forest.dao.TagMapDao;
import com.geeksaga.forest.repositories.TagMapRepository;
import com.geeksaga.forest.repositories.entity.TagMap;

/**
 * @author geeksaga
 * @version 0.1
 */
@Repository
public class TagMapDaoImpl implements TagMapDao
{
    @Autowired
    private TagMapRepository tagMapRepository;

    public TagMap save(TagMap tagMap)
    {
        return tagMapRepository.save(tagMap);
    }
    
    public List<TagMap> save(Iterable<TagMap> list)
    {
        Iterator<TagMap> iter = list.iterator();
        
        while(iter.hasNext())
        {
            TagMap seed = iter.next();
        }
        
        return tagMapRepository.save(list);
    }
    
    public boolean exists(TagMap tagMap)
    {
        return tagMapRepository.exists(tagMap);
    }
    
    // public Seed findBySid(Long sid)
    // {
    // return tagMapRepository.findOne(QSeed.seed.sid.eq(sid));
    // }
}

package com.geeksaga.forest.repositories.jpa;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.dao.TagDao;
import com.geeksaga.forest.repositories.TagRepository;
import com.geeksaga.forest.repositories.entity.Tag;

/**
 * @author geeksaga
 * @version 0.1
 */
@Repository
public class TagDaoImpl extends AbstractSpringDataDao<Tag> implements TagDao
{
    @Autowired
    private TagRepository tagRepository;

    public Tag save(Tag tag)
    {
        tag.setSid(KeyGenerator.generateKeyToLong());
        
        return tagRepository.save(tag);
    }
    
    public List<Tag> save(Iterable<Tag> list)
    {
        Iterator<Tag> iter = list.iterator();
        
        while(iter.hasNext())
        {
            Tag seed = iter.next();
            seed.setSid(KeyGenerator.generateKeyToLong());
        }
        
        return tagRepository.save(list);
    }
    
    public Tag findByTagName(Tag tag)
    {
        return tagRepository.findByTagName(tag.getTagName());
    }
    
    public Tag updateCnt(Tag tag)
    {
        tag.setHitCnt(tag.getHitCnt() + 1);
        
        return tagRepository.save(tag);
    }
    
    public List<Tag> findAll()
    {
        return tagRepository.findAll();
    }
}
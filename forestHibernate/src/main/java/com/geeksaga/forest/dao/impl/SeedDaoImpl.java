/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */

/**
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.entity.QSeed;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.repositories.SeedRepository;

@Repository
public class SeedDaoImpl extends AbstractSpringData<Seed> implements SeedDao
{
    @Autowired
    private SeedRepository seedRepository;
    
    public SeedDaoImpl()
    {
        super(Seed.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.SeedDao#save(com.geeksaga.forest.repositories.entity.Seed)
     */
    public Seed save(Seed seed)
    {
        seed.setSid(KeyGenerator.generateKeyToLong());
        seed.setDelYn("N");
        seed.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        seed.setModifyTimestamp(DateConvertor.getDateTimeFormat());

        seedRepository.save(seed);
        
        return seed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.SeedDao#save(java.lang.Iterable)
     */
    public List<Seed> save(Iterable<Seed> list)
    {
        Iterator<Seed> iter = list.iterator();

        while (iter.hasNext())
        {
            Seed seed = iter.next();
            seed.setSid(KeyGenerator.generateKeyToLong());
            seed.setDelYn("N");
            seed.setRegistTimestamp(DateConvertor.getDateTimeFormat());
            seed.setModifyTimestamp(DateConvertor.getDateTimeFormat());
        }

        seedRepository.save(list);

        return (List<Seed>) list;
    }

    public List<Seed> findAll()
    {
        return seedRepository.findAll();
    }

    // findTop10ByLastnameOrderByFirstnameAsc
    // findTop20ByLastnameOrderByIdDesc
    // findFirstByLastnameOrderByIdDesc
    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.SeedDao#findTopN(int, int)
     */
    public List<Seed> findTopN(int page, int size)
    {
        Pageable pageable = new PageRequest(page, size, Direction.DESC, "modifyTimestamp");

        return seedRepository.findAll(pageable).getContent();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.geeksaga.forest.dao.SeedDao#findBySid(java.lang.Long)
     */
    public Seed findBySid(Long sid)
    {
        return seedRepository.findOne(QSeed.seed.sid.eq(sid));
    }

    public Long modifyById(Seed seed)
    {
        QSeed qSeed = QSeed.seed;

        update(qSeed).where(qSeed.sid.eq(seed.getSid())).set(qSeed.content, seed.getContent()).execute();

        return 0L;
    }

    public Session getSession()
    {
        return (Session) getEntityManager().getDelegate();
    }
}
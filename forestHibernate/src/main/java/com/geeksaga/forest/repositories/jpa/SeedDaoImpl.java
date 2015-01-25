package com.geeksaga.forest.repositories.jpa;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.dao.SeedDao;
import com.geeksaga.forest.repositories.SeedRepository;
import com.geeksaga.forest.repositories.entity.QSeed;
import com.geeksaga.forest.repositories.entity.Seed;

/**
 * @author geeksaga
 * @version 0.1
 */
@Repository
public class SeedDaoImpl extends AbstractSpringDataDao<Seed> implements SeedDao
{
    @Autowired
    private SeedRepository seedRepository;

    public Seed save(Seed seed)
    {
        seed.setSid(KeyGenerator.generateKeyToLong());
        seed.setDelYn("N");
        seed.setRegistTimestamp(DateConvertor.getDateTimeFormat());
        seed.setModifyTimestamp(DateConvertor.getDateTimeFormat());
        
        return seedRepository.save(seed);
    }
    
    public List<Seed> save(Iterable<Seed> list)
    {
        Iterator<Seed> iter = list.iterator();
        
        while(iter.hasNext())
        {
            Seed seed = iter.next();
            seed.setSid(KeyGenerator.generateKeyToLong());
            seed.setDelYn("N");
            seed.setRegistTimestamp(DateConvertor.getDateTimeFormat());
            seed.setModifyTimestamp(DateConvertor.getDateTimeFormat());
        }
        
        return seedRepository.save(list);
    }
    
    public List<Seed> findAll()
    {
        return seedRepository.findAll();
    }
    
    // findTop10ByLastnameOrderByFirstnameAsc
    // findTop20ByLastnameOrderByIdDesc
    // findFirstByLastnameOrderByIdDesc
    public List<Seed> findTopN(int page, int size)
    {
        Pageable pageable = new PageRequest(page, size, Direction.DESC, "modifyTimestamp");
        
        return seedRepository.findAll(pageable).getContent();
    }
    
    public Seed findBySid(Long sid)
    {
        return seedRepository.findOne(QSeed.seed.sid.eq(sid));
    }
}

package com.geeksaga.forest.repositories.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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

    public List<Seed> findAll()
    {
        return seedRepository.findAll();
    }
    
    // findTop10ByLastnameOrderByFirstnameAsc
    // findTop20ByLastnameOrderByIdDesc
    // findFirstByLastnameOrderByIdDesc
    // findTop20ByLastname
    public List<Seed> findTop10()
    {
        Pageable page = new PageRequest(0, 10);
        
        return seedRepository.findAll(page).getContent();
    }
    
    public Seed findBySid(Long sid)
    {
        return seedRepository.findOne(QSeed.seed.sid.eq(sid));
        // return userRepository.findByName(name);
    }
}

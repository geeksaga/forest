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
package com.geeksaga.forest.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.util.DateConvertor;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.entity.QSeed;
import com.geeksaga.forest.entity.Seed;
import com.geeksaga.forest.entity.Tag;
import com.geeksaga.forest.entity.TagMap.CNT_TYPE;
import com.geeksaga.forest.repositories.SeedRepository;
import com.geeksaga.forest.search.LuceneEngine;

// And findByLastnameAndFirstname … where x.lastname = ?1 and x.firstname = ?2
// Or findByLastnameOrFirstname … where x.lastname = ?1 or x.firstname = ?2
// Between findByStartDateBetween … where x.startDate between 1? and ?2
// LessThan findByAgeLessThan … where x.age < ?1
// GreaterThan findByAgeGreaterThan … where x.age > ?1
// IsNull findByAgeIsNull … where x.age is null
// IsNotNull,NotNull findByAge(Is)NotNull … where x.age not null
// Like findByFirstnameLike … where x.firstname like ?1
// NotLike findByFirstnameNotLike … where x.firstname not like ?1
// OrderBy findByAgeOrderByLastnameDesc … where x.age = ?1 order by x.lastname desc
// Not findByLastnameNot … where x.lastname <> ?1
// In findByAgeIn(Collection<Age> ages) … where x.age in ?1
// NotIn findByAgeNotIn(Collection<Age> age) … where x.age not in ?1
@Service
public class SeedCommandService extends AbstractSpringData<Seed>
{
    @Autowired
    private SeedRepository seedRepository;

    @Autowired
    private TagMapService tagMapService;

    private LuceneEngine luceneEngine = LuceneEngine.getInstance();

    public SeedCommandService()
    {
        super(Seed.class);
    }

    /**
     * <pre>
     * Seed 를 추가(등록) 한다.
     * 
     * 주의!. JPA 의 repository.save(seed) 는 저장하려는 seed 값이 아니라 저장 하려고 한 키를 값으로 데이터가 있는지 조회 한 값을 리턴 한다.
     * 테이블 참조 관계가 있는 값들은 초기화가 된다.(ex. 태그에 대한 참조 값이 사라진다)
     * </pre>
     * 
     * @param seed
     * @return
     */
    @Transactional
    public Seed save(Seed seed)
    {
        try
        {
            luceneEngine.addDocument(seed.getIndexingData(), seed.getSid());

            seed.setSid(KeyGenerator.generateKeyToLong());
            seed.setRegistTimestamp(DateConvertor.getDateTimeFormat());
            seed.setModifyTimestamp(DateConvertor.getDateTimeFormat());

            if (seed.getSid().equals(seedRepository.save(seed).getSid()))
            {
                if (!StringUtils.isEmpty(seed.getTag()))
                {
                    Tag tags = new Tag();
                    tags.setTargetSid(seed.getSid());
                    tags.setTagName(seed.getTag());

                    tagMapService.save(seed, tags, CNT_TYPE.TAG_PLUS_CNT);
                }
            }
        }
        catch (Throwable thr)
        {
            luceneEngine.deleteDocument(seed.getSid());

            thr.printStackTrace();
        }

        return seed;
    }

    public List<Seed> save(Iterable<Seed> list)
    {
        Iterator<Seed> iter = list.iterator();

        while (iter.hasNext())
        {
            Seed seed = iter.next();
            seed.setSid(KeyGenerator.generateKeyToLong());
            seed.setRegistTimestamp(DateConvertor.getDateTimeFormat());
            seed.setModifyTimestamp(DateConvertor.getDateTimeFormat());
        }

        seedRepository.save(list);

        return (List<Seed>) list;
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
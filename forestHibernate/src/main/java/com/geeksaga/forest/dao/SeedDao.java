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
package com.geeksaga.forest.dao;

import java.util.List;

import com.geeksaga.forest.entity.Seed;

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
// NotIn   findByAgeNotIn(Collection<Age> age) … where x.age not in ?1

public interface SeedDao
{
    /**
     * <pre>
     * Seed 를 저장 한다.
     * 
     * 주의!. JPA 의 repository.save(seed) 는 저장하려는 seed 값이 아니라 저장 하려고 한 키를 값으로 데이터가 있는지 조회 한 값을 리턴 한다. 
     * </pre>
     * @param seed
     * @return
     */
    Seed save(Seed seed);
    
    List<Seed> save(Iterable<Seed> list);
    
    Seed findBySid(Long sid);
    
    List<Seed> findTopN(int page, int size);
}
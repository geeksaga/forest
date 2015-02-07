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
package com.geeksaga.forest.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.geeksaga.forest.entity.Seed;

@Repository
public interface SeedRepository extends JpaRepository<Seed, Long>, QueryDslPredicateExecutor<Seed>, JpaSpecificationExecutor<Seed>
{
    // @Query(value = "SELECT s FROM pw_seeds s WHERE s.delYn = ?1", nativeQuery = false)
    // List<Seed> findByDelYn(String delYn);
    
    List<Seed> findByModifyTimestamp(String modifyTimestamp, Pageable page);
    
    List<Seed> findTop10ByModifyTimestampOrderByModifyTimestampDesc(String modifyTimestamp);
}
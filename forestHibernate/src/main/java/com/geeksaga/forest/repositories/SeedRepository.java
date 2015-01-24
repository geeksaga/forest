package com.geeksaga.forest.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.geeksaga.forest.repositories.entity.Seed;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface SeedRepository extends JpaRepository<Seed, Long>, QueryDslPredicateExecutor<Seed>
{
    // @Query(value = "SELECT s FROM pw_seeds s WHERE s.delYn = ?1", nativeQuery = false)
    // List<Seed> findByDelYn(String delYn);
    
    // findTop10ByLastnameOrderByFirstnameDesc
    List<Seed> findByModifyTimestamp(String modifyTimestamp, Pageable page);
    
    List<Seed> findTop10ByModifyTimestampOrderByModifyTimestampDesc(String modifyTimestamp);
}
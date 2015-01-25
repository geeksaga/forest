package com.geeksaga.forest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.geeksaga.forest.repositories.entity.TagMap;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface TagMapRepository extends JpaRepository<TagMap, TagMap>, QueryDslPredicateExecutor<TagMap>
{}
package com.geeksaga.forest.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.geeksaga.forest.entity.User;
import com.geeksaga.forest.entity.UserManager;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User>
{
    Page<User> findByUserManagerSid(Long userManagerSid, Pageable pageable);

    List<User> findByUserManagerSid(Long userManagerSid, Sort sort);

    List<User> findByUserManager(UserManager userManager);
}
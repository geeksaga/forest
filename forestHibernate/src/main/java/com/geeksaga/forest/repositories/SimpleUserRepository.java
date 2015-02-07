package com.geeksaga.forest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.geeksaga.forest.entity.User;

public interface SimpleUserRepository extends CrudRepository<User, Integer>
{
    User findByName(String name);

//    List<User> findByName(String name);

//    @Query("select u from User u where u.name = ?")
//    List<User> findByFirstname(String name);

    // @Query("select u from User u where u.name = :name or u.name = :name")
    // List<User> findByFirstnameOrLastname(@Param("name") String name);
}
package com.geeksaga.forest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geeksaga.forest.repositories.entity.UserManager;

public interface UserManagerRepository extends JpaRepository<UserManager, Long>
{}
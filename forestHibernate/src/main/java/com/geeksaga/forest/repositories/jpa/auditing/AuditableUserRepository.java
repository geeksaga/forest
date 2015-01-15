package com.geeksaga.forest.repositories.jpa.auditing;

import org.springframework.data.repository.CrudRepository;

public interface AuditableUserRepository extends CrudRepository<AuditableUser, Long>
{}
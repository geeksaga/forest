package com.geeksaga.forest.repositories.jpa.auditing;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractAuditable;

@Entity
public class AuditableUser extends AbstractAuditable<AuditableUser, Long>
{
    private static final long serialVersionUID = 1L;

    private String username;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
}
package com.geeksaga.forest.repositories.jpa.auditing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.util.ReflectionTestUtils;

import com.geeksaga.forest.util.AbstractRepositoryTestSupport;

public class AuditableUserTest extends AbstractRepositoryTestSupport
{
    @Autowired
    private AuditableUserRepository repository;
    @Autowired
    private AuditorAwareImpl auditorAware;
    @Autowired
    private AuditingEntityListener listener;

    @Test
    public void auditEntityCreation() throws Exception
    {
        assertThat(ReflectionTestUtils.getField(listener, "handler"), is(notNullValue()));

        AuditableUser user = new AuditableUser();
        user.setUsername("geeksaga");

        auditorAware.setAuditor(user);

        user = repository.save(user);
        user = repository.save(user);

        assertEquals(user, user.getCreatedBy());
        assertEquals(user, user.getLastModifiedBy());
    }
}
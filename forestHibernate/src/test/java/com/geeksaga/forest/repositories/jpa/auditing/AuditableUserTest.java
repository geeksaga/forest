package com.geeksaga.forest.repositories.jpa.auditing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = AuditingConfiguration.class)
public class AuditableUserTest {

	@Autowired AuditableUserRepository repository;
	@Autowired AuditorAwareImpl auditorAware;
	@Autowired AuditingEntityListener listener;

	@Test
	public void auditEntityCreation() throws Exception {

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

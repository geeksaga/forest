package com.geeksaga.forest.repositories.jpa.auditing;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<AuditableUser>
{
    private AuditableUser auditor;

    public void setAuditor(AuditableUser auditor)
    {
        this.auditor = auditor;
    }

    public AuditableUser getCurrentAuditor()
    {
        return auditor;
    }
}
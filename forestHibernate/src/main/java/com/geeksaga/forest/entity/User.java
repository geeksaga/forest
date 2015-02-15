/*
 * GeekSaga Class Infomation Library v0.0.1
 * 
 * http://geeksaga.com/
 * 
 * Copyright 2014 GeekSaga Foundation, Inc. and other contributors
 * 
 * Released under the MIT license http://geeksaga.com/license
 */

/**
 * @author geeksaga
 * @version 0.1
 */
package com.geeksaga.forest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pw_users", schema = "")
public class User extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 4, max = 255)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 4, max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 255)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled;
    
    @Column(name = "account_non_locked", nullable = false, columnDefinition = "boolean default true")
    private boolean accountNonLocked;
    
    @Column(name = "account_non_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean accountNonExpired;
    
    @Column(name = "credentials_non_expired", nullable = false, columnDefinition = "boolean default true")
    private boolean credentialsNonExpired;

    @Transient
    private String authority;

    // @JsonManagedReference columnDefinition = "boolean default true"
    // @OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "user_sid", nullable = true, table = "pw_roles")
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_manager_sid", nullable = true, insertable = true, updatable = false)
    private UserManager userManager;

    public User()
    {}

    public User(Long sid, String email, String password, String firstName, String lastName)
    {
        this(email, password, firstName, lastName);

        setSid(sid);
    }

    public User(String email, String password, String firstName, String lastName)// , Collection<GrantedAuthority> authorities)
    {
        this(email, password, firstName, lastName, true, true, true, true);

        // setAuthorities(authorities);
    }

    public User(String email, String password, String firstName, String lastName, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked)
    {
        setEmail(email);
        setPassword(password);
        setFirstName(firstName);
        setLastName(lastName);
        setEnabled(enabled);
        setAccountNonExpired(accountNonLocked);
        setCredentialsNonExpired(credentialsNonExpired);
        setAccountNonLocked(accountNonLocked);
    }

    public UserManager getUserManager()
    {
        return userManager;
    }

    public void setUserManager(UserManager userManager)
    {
        this.userManager = userManager;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired)
    {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return "User [sid=" + sid + ", name=" + firstName + ", email=" + email + "]";
    }
}
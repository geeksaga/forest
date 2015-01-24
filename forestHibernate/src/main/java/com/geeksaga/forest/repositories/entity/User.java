package com.geeksaga.forest.repositories.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author geeksaga
 * @version 0.1
 */
@Entity
@Table(name = "pw_users", schema = "")
public class User extends BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "name")
    private String name;

    // @JsonManagedReference
    // @OneToOne(mappedBy = "user", cascade = { CascadeType.ALL })
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinColumn(name = "user_sid", nullable = true, table = "pw_roles")
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_manager_sid", nullable = true, insertable = true, updatable = false)
    private UserManager userManager;

    // public User(String id, String password, Collection<GrantedAuthority> authorities)
    // {
    // setId(id);
    // setPassword(password);
    // setEnabled(true);
    // setAccountNonExpired(true);
    // setCredentialsNonExpired(true);
    // setAccountNonLocked(true);
    // setAuthorities(authorities);
    // }
    //
    // public User(String id, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
    // boolean accountNonLocked, Collection<GrantedAuthority> authorities) throws IllegalArgumentException
    // {
    //
    // setId(id);
    // setPassword(password);
    // setEnabled(enabled);
    // setAccountNonExpired(accountNonLocked);
    // setCredentialsNonExpired(credentialsNonExpired);
    // setAccountNonLocked(accountNonLocked);
    // setAuthorities(authorities);
    // }

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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        return "User [sid=" + sid + ", name=" + name + ", email=" + email + "]";
    }
}
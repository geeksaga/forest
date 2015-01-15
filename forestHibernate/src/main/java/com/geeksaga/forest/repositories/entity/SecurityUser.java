package com.geeksaga.forest.repositories.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends User implements UserDetails
{
    private static final long serialVersionUID = 1L;

    // TODO 권한 관련 내용을 DB 상에는 varchar(1)로 저장하고 있다.
    // boolean 타입을 사용해야 하나? 아니면 변환해서 처리 해야하나?
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private Collection<GrantedAuthority> authorities;

    public SecurityUser()
    {}

    public SecurityUser(User user)
    {
        if (user != null)
        {
            this.setSid(user.getSid());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setName(user.getName());
            this.setRoles(user.getRoles());
        }
    }

    public SecurityUser(String email, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
            boolean accountNonLocked, Collection<GrantedAuthority> authorities) throws IllegalArgumentException
    {
        setEmail(email);
        setPassword(password);
        setEnabled(enabled);
        setAccountNonExpired(accountNonLocked);
        setCredentialsNonExpired(credentialsNonExpired);
        setAccountNonLocked(accountNonLocked);
        setAuthorities(authorities);
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities()
//    {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        Set<Role> userRoles = this.getRoles();
//        if (userRoles != null)
//        {
//            for (Role role : userRoles)
//            {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
//                authorities.add(authority);
//            }
//        }
//
//        return authorities;
//    }

    public boolean existsAuthorities(String auth)
    {
        for (GrantedAuthority authority : getAuthorities())
        {
            if (authority.getAuthority().equals(auth))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getPassword()
    {
        return super.getPassword();
    }

    @Override
    public String getUsername()
    {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void setAccountNonExpired(boolean accountNonExpired)
    {
        this.accountNonExpired = accountNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired)
    {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    public Collection<GrantedAuthority> getAuthorities()
    {
        if (authorities == null)
        {
            return new ArrayList<GrantedAuthority>();
        }

        return authorities;
    }
    
    public void setAuthorities(Collection<GrantedAuthority> authorities)
    {
        this.authorities = authorities;
    }
}
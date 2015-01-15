package com.geeksaga.forest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.geeksaga.forest.repositories.entity.User;

public abstract class LoginService implements UserDetailsService
{
//    private SqlSession sqlSession;
//
//    public void setSqlSession(SqlSession sqlSession)
//    {
//        this.sqlSession = sqlSession;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
//    {
//        com.goodcodes.security.Member ur = sqlSession.getMapper(LoginMapper.class).getUser(username);
//        String password = ur.getPwd();
//        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
//
//        String[] roles = ur.getRole().split(",");
//        for (String role : roles)
//        {
//            list.add(new SimpleGrantedAuthority(role));
//        }
//        UserDetails user = new User(username, password, list);
//        return user;
//    }
}
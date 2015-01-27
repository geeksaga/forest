package com.geeksaga.forest.service;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.repositories.entity.SecurityUser;

/**
 * @author geeksaga
 * @version 0.1
 */
@Service(BeanIds.USER_DETAILS_SERVICE)
public class AuthorityService implements UserDetailsService
{
    private static final Log logger = LogFactory.getLog(AuthorityService.class);

    @Autowired
    protected UserDao userDao;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException
    {
        // StringTokenizer st = new StringTokenizer(email, "___");
        String password = "aabcb987e4b425751e210413562e78f776de6285";
        
        SecurityUser securityUser = new SecurityUser();
        securityUser.setEmail(email);
        StandardPasswordEncoder spe = new StandardPasswordEncoder();
        securityUser.setPassword(spe.encode("password"));
        securityUser.setEnabled(true);
        securityUser.setAccountNonLocked(true);
        securityUser.setAccountNonExpired(true);
        securityUser.setCredentialsNonExpired(true);
        
        // user.setPid(st.nextToken());

        SecurityUser authenticateUser = null;

        try
        {
            authenticateUser = userDao.authenticate(securityUser);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage(), e);
        }

        return (authenticateUser == null) ? new SecurityUser() : authenticateUser;
    }

    public static SecurityUser getUser(Object object)
    {
        SecurityContextImpl securityContextImpl = null;

        if (object instanceof HttpSession)
        {
            securityContextImpl = (SecurityContextImpl) ((HttpSession) object).getAttribute("SPRING_SECURITY_CONTEXT");

            Authentication authentication = securityContextImpl.getAuthentication();

            return (SecurityUser) authentication.getPrincipal();
        }

        return new SecurityUser();
    }

    public static SecurityUser getUser(WebRequest request)
    {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getAttribute("SPRING_SECURITY_CONTEXT",
                WebRequest.SCOPE_SESSION);

        if (securityContextImpl == null)
        {
            return new SecurityUser();
        }

        Authentication authentication = securityContextImpl.getAuthentication();

        return (SecurityUser) authentication.getPrincipal();
    }

    public static SecurityUser getUser(HttpSession session)
    {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");

        if (securityContextImpl == null)
        {
            return new SecurityUser();
        }

        Authentication authentication = securityContextImpl.getAuthentication();

        return (SecurityUser) authentication.getPrincipal();
    }
}
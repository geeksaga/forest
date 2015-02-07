package com.geeksaga.forest.service;

import java.util.List;

import com.geeksaga.forest.entity.SecurityUser;
import com.geeksaga.forest.entity.User;

/**
 * @author geeksaga
 * @version 0.1
 */
public interface UserCommandService
{
     List<User> listup();
 
     SecurityUser authenticate(SecurityUser securityUser);
     
    // List<History> getHistories(int userId);
    // void setUserLevelService(UserLevelService userLevelService);
}

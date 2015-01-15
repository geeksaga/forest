package com.geeksaga.forest.service;

import java.util.List;

import com.geeksaga.forest.repositories.entity.User;


public interface UserService
{
    boolean rent(Long userId, int bookId);

    boolean returnBook(Long userId, int bookId);

     List<User> listup();
     
    // List<History> getHistories(int userId);
    // void setUserLevelService(UserLevelService userLevelService);
}

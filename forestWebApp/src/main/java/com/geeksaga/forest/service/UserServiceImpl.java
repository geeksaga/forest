package com.geeksaga.forest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.repositories.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService
{

    // @Autowired
    // private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    // @Autowired
    // private HistoryDao historyDao;

    public UserServiceImpl()
    {}

//    public UserServiceImpl(BookDao bookDao, UserDao userDao, HistoryDao historyDao)
//    {
//        this.bookDao = bookDao;
//        this.userDao = userDao;
//        this.historyDao = historyDao;
//    }

//    @Autowired
//    private UserLevelService userLevelService;

    @Override
    public boolean rent(Long userId, int bookId)
    {
        User user = userDao.getById(userId);
//        Book book = bookDao.getById(bookId);
//
//        if (book.getRentUser() != null)
//        {
//            throw new IllegalArgumentException("It's already rent book");
//        }
//        user.setPoint(user.getPoint() + 1);
//        user.setLevel(userLevelService.getUserLevel(user.getPoint()));
//        book.setRentUser(user);
//        book.setStatus(BookStatus.RENTNOW);
//
//        History history = new History();
//        history.setBook(book);
//        history.setUser(user);
//        history.setActionType(ActionType.RENT);
//
//        historyDao.add(history);
//        bookDao.update(book);
//        userDao.update(user);
        return true;
    }

    @Override
    public boolean returnBook(Long userId, int bookId)
    {
        User user = userDao.getById(userId);
//        Book book = bookDao.getById(bookId);
//
//        if (book.getRentUser() == null)
//        {
//            throw new IllegalStateException("Book do not has rent user!");
//        }
//        else if (user.getId() != book.getRentUser().getId())
//        {
//            throw new IllegalStateException("Rent User is not same in this book");
//        }
//
//        book.setRentUser(null);
//        book.setStatus(BookStatus.CANRENT);
//
//        History history = new History();
//        history.setBook(book);
//        history.setUser(user);
//        history.setActionType(ActionType.RETURN);
//
//        historyDao.add(history);
//        bookDao.update(book);

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listup()
    {
        return userDao.getAll();
    }

    // @Transactional(readOnly = true)
    // @Override
    // public List<History> getHistories(int userId)
    // {
    // return null;
    // }
    //
    // @Override
    // public void setUserLevelService(UserLevelService userLevelService)
    // {
    // this.userLevelService = userLevelService;
    // }
}

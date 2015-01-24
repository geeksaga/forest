package com.geeksaga.forest.service;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.geeksaga.common.crypt.PasswordEncoderWrapper;
import com.geeksaga.common.util.KeyGenerator;
import com.geeksaga.forest.config.DataConfig;
import com.geeksaga.forest.dao.UserDao;
import com.geeksaga.forest.repositories.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { HibernateConfiguration.class })
@ContextConfiguration(classes = { DataConfig.class })
@Transactional
public class UserServiceImplTest
{

    @Autowired
    private UserService userService;
    // @Autowired
    // private BookService bookService;
    //
    // @Autowired
    // private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    // @Autowired
    // private HistoryDao historyDao;

    @Before
    public void setUp()
    {
        // historyDao.deleteAll();
        // bookDao.deleteAll();
        userDao.deleteAll();

        addTestData();
    }

    private void addTestData()
    {
        Date now = new Date();

        // for (int i = 0; i < 10; i++)
        // {
        // Book book = new Book();
        // book.setName("BOOK_NAME_" + i);
        // book.setAuthor("AUTHOR_" + i);
        // book.setPublishDate(new Timestamp(now.getTime()));
        // book.setStatus(BookStatus.CANRENT);
        // bookDao.add(book);
        // }

        User point99User = new User();
        // point99User.setPoint(99);
        point99User.setSid(KeyGenerator.generateKeyToLong());
        point99User.setName("point99");
        point99User.setEmail("geeksaga99@geeksaga.com");
        point99User.setPassword(PasswordEncoderWrapper.encode("password"));
        // point99User.setLevel(UserLevel.NORMAL);
        userDao.add(point99User);

        User point299User = new User();
        point299User.setSid(KeyGenerator.generateKeyToLong());
        // point299User.setPoint(299);
        point299User.setName("point199");
        point299User.setEmail("geeksaga199@geeksaga.com");
        point299User.setPassword(PasswordEncoderWrapper.encode("password"));
        // point299User.setLevel(UserLevel.MASTER);
        userDao.add(point299User);

        User point301User = new User();
        // point301User.setPoint(301);
        point301User.setSid(KeyGenerator.generateKeyToLong());
        point301User.setName("point299");
        point301User.setEmail("geeksaga299@geeksaga.com");
        point301User.setPassword(PasswordEncoderWrapper.encode("password"));
        // point301User.setLevel(UserLevel.MVP);
        userDao.add(point301User);
    }

    @Test
    public void testRent() throws Exception
    {
        rent();
    }

    private Book rent() throws Exception
    {
        User point99User = userDao.findByName("point99");
        
        assertThat(point99User, is(not(nullValue())));
        // Book book = bookService.listup().get(0);
        //
        // userService.rent(point99User.getId(), book.getId());
        // point99User = userDao.findByName("point99");
        //
        // book = bookDao.getById(book.getId());
        // assertThat(point99User.getPoint(), is(100));
        // assertThat(point99User.getLevel(), is(UserLevel.MASTER));
        // assertThat(book.getStatus(), is(BookStatus.RENTNOW));

        return null;
    }

    @Test
    public void testReturnBook() throws Exception
    {
        Book rentedBook = rent();
        // User rentUser = rentedBook.getRentUser();
        // userService.returnBook(rentUser.getId(), rentedBook.getId());
        // rentedBook = bookDao.getById(rentedBook.getId());
        // assertThat(rentedBook.getStatus(), is(BookStatus.CANRENT));
        // assertThat(rentedBook.getRentUser(), is(nullValue()));
    }

    @Test
    public void testReturnBookWithException() throws Exception
    {}

    @Test
    public void testListup() throws Exception
    {
        List<User> listup = userService.listup();
        for (User user : listup)
        {
            System.out.println(user);
        }
    }

    @Test
    public void testGetHistories() throws Exception
    {}

    @Test
    public void testSetUserLevelService() throws Exception
    {}
}
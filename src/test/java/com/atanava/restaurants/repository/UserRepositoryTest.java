package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Role;
import com.atanava.restaurants.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static com.atanava.restaurants.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void save() {
        User saved = repository.save(getNew());
        int newId = saved.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(saved, newUser);
        USER_MATCHER.assertMatch(repository.get(newId), newUser);
    }

    @Test
    public void duplicateSaved() throws Exception {
        assertThrows(DataAccessException.class, () ->
                repository.save(new User(null, "Duplicate", "user1@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        repository.delete(USER1_ID);
        assertNull(repository.get(USER1_ID));
    }

    @Test
    public void get() {
        User user = repository.get(USER1_ID);
        USER_MATCHER.assertMatch(user, USER1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertNull(repository.get(NOT_FOUND));
    }

    @Test
    public void getByEmail() {
        User user = repository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, ADMIN);
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        repository.save(updated);
        USER_MATCHER.assertMatch(repository.get(USER1_ID), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = repository.getAll();
        USER_MATCHER.assertMatch(all, ADMIN, USER1, USER2);
    }

//    @Test
//    public void getWithVotes() {
//    }
}
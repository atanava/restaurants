package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Role;
import com.atanava.restaurants.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;
import static com.atanava.restaurants.DbSequence.*;
import static com.atanava.restaurants.UserTestData.*;

public class DataJpaUserRepositoryTest extends AbstractTest {

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
    public void duplicateMailSave() throws Exception {
        assertThrows(DataAccessException.class, () ->
                repository.save(new User(null, "Duplicate", "user1@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    public void delete() {
        repository.delete(USER1_ID.value);
        assertNull(repository.get(USER1_ID.value));
    }

    @Test
    public void deletedNotFound() throws Exception {
        assertFalse(repository.delete(NOT_FOUND.value));
    }

    @Test
    public void get() {
        User user = repository.get(USER1_ID.value);
        USER_MATCHER.assertMatch(user, expectedUser1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertNull(repository.get(NOT_FOUND.value));
    }

    @Test
    public void getByEmail() {
        User user = repository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, expectedAdmin);
    }

    @Test
    public void update() throws Exception {
        User updated = getUpdated();
        repository.save(updated);
        USER_MATCHER.assertMatch(repository.get(USER1_ID.value), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = repository.getAll();
        USER_MATCHER.assertMatch(all, expectedUser1, expectedAdmin, expectedUser2);
    }

//    @Test
//    public void getWithVotes() {
//    }
}
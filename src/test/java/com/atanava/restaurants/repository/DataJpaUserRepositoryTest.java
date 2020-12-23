package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.repository.user.UserRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.UserTestData.*;

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
    public void duplicateMailSave() {
        assertThrows(DataAccessException.class, () -> repository.save(getDuplicate()));
    }

    @Test
    public void update() {
        User updated = getUpdated();
        repository.save(updated);
        USER_MATCHER.assertMatch(repository.get(USER_1.id), getUpdated());
    }

    @Test
    public void delete() {
        assertTrue(repository.delete(USER_1.id));
        assertThrows(NotFoundException.class, () -> repository.get(USER_1.id));
    }

    @Test
    public void deletedNotFound() {
        assertFalse(repository.delete(NOT_FOUND.id));
    }

    @Test
    public void get() {
        User user = repository.get(USER_1.id);
        USER_MATCHER.assertMatch(user, user1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> repository.get(NOT_FOUND.id));
    }

    @Test
    public void getByEmail() {
        User user = repository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    public void getAll() {
        List<User> all = repository.getAll();
        USER_MATCHER.assertMatch(all, user1, admin, user2);
    }
}
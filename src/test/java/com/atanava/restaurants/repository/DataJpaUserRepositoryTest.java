package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.repository.user.UserRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.UserTestData.*;

public class DataJpaUserRepositoryTest extends AbstractTest {

    @Autowired
    UserRepository repository;

    @Autowired
    protected JpaUtil jpaUtil;

    @BeforeEach
    void setUp() {
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void save() {
        User saved = repository.save(getNew());
        int newId = saved.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(saved, newUser);
        USER_MATCHER.assertMatch(repository.get(newId), newUser);
    }

    @Test
    void duplicateMailSave() {
        assertThrows(DataAccessException.class, () -> repository.save(getDuplicate()));
    }

    @Test
    void update() {
        User updated = getUpdated();
        repository.save(updated);
        USER_MATCHER.assertMatch(repository.get(USER_1.id), getUpdated());
    }

    @Test
    void delete() {
        assertTrue(repository.delete(USER_1.id));
        assertThrows(NotFoundException.class, () -> repository.get(USER_1.id));
    }

    @Test
    void deletedNotFound() {
        assertFalse(repository.delete(NOT_FOUND.id));
    }

    @Test
    void get() {
        User user = repository.get(USER_1.id);
        USER_MATCHER.assertMatch(user, user1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> repository.get(NOT_FOUND.id));
    }

    @Test
    void getByEmail() {
        User user = repository.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getAll() {
        List<User> all = repository.getAll();
        USER_MATCHER.assertMatch(all, user1, admin, user2);
    }
}
package com.atanava.restaurants.service;

import com.atanava.restaurants.dto.UserTo;
import com.atanava.restaurants.model.Role;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.repository.JpaUtil;
import com.atanava.restaurants.testdata.UserTestData;
import com.atanava.restaurants.util.UserUtil;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.UserTestData.*;
import static com.atanava.restaurants.testdata.DbSequence.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Autowired
    protected JpaUtil jpaUtil;

    @BeforeEach
    void setUp() {
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void create() {
        User created = service.create(getNew());
        int newId = created.id();
        User newUser = getNew();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);

    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user1@yandex.ru", "newPass", Role.USER)));
    }

    @Test
    void delete() {
        service.delete(USER_1.id);
        assertThrows(NotFoundException.class, () -> service.get(USER_1.id));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND.id));
    }

    @Test
    void get() {
        User user = service.get(ADMIN.id);
        USER_MATCHER.assertMatch(user, UserTestData.admin);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND.id));
    }

    @Test
    void getByEmail() {
        User user = service.getByEmail("admin@gmail.com");
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, user1, admin, user2);
    }

    @Test
    void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_1.id), getUpdated());

    }

    @Test
    void updateByTo() {
        UserTo updatedTo = UserUtil.asTo(getUpdated());
        service.update(updatedTo);
        USER_MATCHER.assertMatch(service.get(USER_1.id), getUpdated());
    }

    @Test
    void enable() {
        service.enable(USER_1.id, false);
        assertFalse(service.get(USER_1.id).isEnabled());
        service.enable(USER_1.id, true);
        assertTrue(service.get(USER_1.id).isEnabled());
    }

    @Test
    void createWithException() {
        validateRootCause(() -> service.create(new User(null, "  ", "mail@yandex.ru", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "mail@yandex.ru", "  ", Role.USER)), ConstraintViolationException.class);
    }

}
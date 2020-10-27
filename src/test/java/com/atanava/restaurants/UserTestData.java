package com.atanava.restaurants;

import com.atanava.restaurants.model.Role;
import com.atanava.restaurants.model.User;

import static com.atanava.restaurants.DbSequence.*;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator("registered", "roles", "votes");

    public static final User expectedAdmin = new User(ADMIN_ID.value, "Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final User expectedUser1 = new User(USER1_ID.value, "User1", "user1@yandex.ru", "password", Role.USER);
    public static final User expectedUser2 = new User(USER2_ID.value, "User2", "user2@hot.ee", "password", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(expectedUser1);
        updated.setName("UpdatedName");
        updated.setEmail("updated@gmail.com");
        return updated;
    }
}

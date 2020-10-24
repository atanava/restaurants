package com.atanava.restaurants;

import com.atanava.restaurants.model.Role;
import com.atanava.restaurants.model.User;

import static com.atanava.restaurants.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator("registered", "roles", "votes");

    public static final int NOT_FOUND = 10;
    public static final int ADMIN_ID = START_SEQ;
    public static final int USER1_ID = START_SEQ + 1;
    public static final int USER2_ID = START_SEQ + 2;

    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final User USER1 = new User(USER1_ID, "User1", "user1@yandex.ru", "password", Role.USER);
    public static final User USER2 = new User(USER2_ID, "User2", "user2@hot.ee", "password", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(USER1);
        updated.setName("UpdatedName");
        updated.setEmail("updated@gmail.com");
        return updated;
    }
}

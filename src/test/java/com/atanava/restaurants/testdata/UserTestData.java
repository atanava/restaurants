package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Role;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.web.json.JsonUtil;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingIgnoringFieldsComparator(User.class, "registered", "roles", "votes", "password");

    public static final User admin = new User(ADMIN.id, "Vasja", "admin@gmail.com", "admin", Role.ADMIN);
    public static final User user1 = new User(USER_1.id, "Fedja", "user1@yandex.ru", "password", Role.USER);
    public static final User user2 = new User(USER_2.id, "Vasja", "user2@hot.ee", "password", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Role.USER);
    }

    public static User getDuplicate() {
        return new User(null, "Duplicate", user1.getEmail(), "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(user1);
        updated.setName("UpdatedName");
        updated.setEmail("updated@gmail.com");
        return updated;
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

}

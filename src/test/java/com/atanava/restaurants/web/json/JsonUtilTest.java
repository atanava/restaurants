package com.atanava.restaurants.web.json;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.testdata.UserTestData;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.assertEquals;
import static com.atanava.restaurants.testdata.DishTestData.*;

public class JsonUtilTest {

    @Test
    public void readWriteValue() {
        Dish dish = getAllFromRest1().get(0);
        String json = JsonUtil.writeValue(dish);
        System.out.println(json);
        Dish dishFromJson = JsonUtil.readValue(json, Dish.class);
        DISH_MATCHER.assertMatch(dishFromJson, dish);
    }

    @Test
    public void readWriteValues() {
        List<Dish> dishes = getAllFromRest1();
        String json = JsonUtil.writeValue(dishes);
        System.out.println(json);
        List<Dish> dishesFromJson = JsonUtil.readValues(json, Dish.class);
        DISH_MATCHER.assertMatch(dishesFromJson, dishes);
    }

    @Test
    public void writeOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.user1);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = UserTestData.jsonWithPassword(UserTestData.user1, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}

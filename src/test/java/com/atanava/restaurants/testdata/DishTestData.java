package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class DishTestData {

    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Dish.class, "restaurant", "menus");

    public static Dish getNew() {
        Dish dish = new Dish(null, "New", null, 800);
        dish.setRestaurantId(RESTAURANT_1.id);
        return dish;
    }

    public static Dish getDuplicate() {
        Dish duplicate = getAllFromRest1().get(0);
        duplicate.setId(null);
        return duplicate;
    }

    public static Dish getDeactivated() {
        Dish deactivated = getAllFromRest1().get(0);
        deactivated.setActive(false);
        return deactivated;
    }

    public static List<Dish> getAllFromRest1() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH_1.id, "Salad", null, 400));
        expectedList.add(new Dish(DISH_2.id, "Soup", null, 530));
        expectedList.add(new Dish(DISH_3.id, "Meat", null, 750));
        expectedList.add(new Dish(DISH_4.id, "Fish", null, 960));
        expectedList.add(new Dish(DISH_5.id, "Juice", null, 200));

        expectedList.forEach(dish -> dish.setRestaurantId(RESTAURANT_1.id));
        return expectedList;
    }

    public static List<Dish> getAllFromRest2() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH_6.id, "Salad", null, 320));
        expectedList.add(new Dish(DISH_7.id, "Soup", null, 400));
        expectedList.add(new Dish(DISH_8.id, "Meat", null, 550));
        expectedList.add(new Dish(DISH_9.id, "Fish", null, 650));
        expectedList.add(new Dish(DISH_10.id, "Juice", null, 120));

        expectedList.forEach(dish -> dish.setRestaurantId(RESTAURANT_2.id));
        return expectedList;
    }

    public static List<Dish> getAllFromRest1Sorted() {
        List<Dish> expectedList = getAllFromRest1();
        expectedList.sort(Comparator.comparing(Dish::getName));
        return expectedList;
    }
}

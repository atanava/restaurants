package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class DishTestData {

    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Dish.class,"restaurant", "restaurantId", "menus");

    private static final Restaurant troika =  RestaurantTestData.rest1;
    private static final Restaurant gloria = RestaurantTestData.rest2;

    public static Dish getNew() {
        return new Dish(null, "New", troika, 800);
    }

    public static Dish getDuplicate() {
        return getAllFromRest1().get(0);
    }

    public static Dish getUpdated() {
        Dish updated = getAllFromRest1().get(0);
        updated.setPrice(350);
        return updated;
    }

    public static Dish getDeactivated() {
        Dish deactivated = getAllFromRest1().get(0);
        deactivated.setActive(false);
        return deactivated;
    }

    public static List<Dish> getAllFromRest1() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH_1.id, "Salad", troika, 400));
        expectedList.add(new Dish(DISH_2.id, "Soup", troika, 530));
        expectedList.add(new Dish(DISH_3.id, "Meat", troika, 750));
        expectedList.add(new Dish(DISH_4.id, "Fish", troika, 960));
        expectedList.add(new Dish(DISH_5.id, "Juice", troika, 200));
        return expectedList;
    }

    public static List<Dish> getAllFromRest2() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH_6.id, "Salad", gloria, 320));
        expectedList.add(new Dish(DISH_7.id, "Soup", gloria, 400));
        expectedList.add(new Dish(DISH_8.id, "Meat", gloria, 550));
        expectedList.add(new Dish(DISH_9.id, "Fish", gloria, 650));
        expectedList.add(new Dish(DISH_10.id, "Juice", gloria, 120));
        return expectedList;
    }

    public static List<Dish> getAllFromRest1Sorted() {
        List<Dish> expectedList = getAllFromRest1();
        expectedList.sort(Comparator.comparing(Dish::getName));
        return expectedList;
    }
}

package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class DishTestData {

    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsComparator("restaurant", "menus");

    private static final Restaurant troika =  RestaurantTestData.rest1;
    private static final Restaurant gloria = RestaurantTestData.rest2;

    public static Dish getNew() {
        return new Dish(null, troika, "New", 800);
    }

    public static Dish getDuplicate() {
        return new Dish(new Dish(null, troika, getAllFromRest1().get(0).getName(), 300));
    }

    public static Dish getUpdated() {
        Dish updated = getAllFromRest1().get(0);
        updated.setName("UpdatedName");
        return updated;
    }

    public static Dish getDeactivated() {
        Dish deactivated = getAllFromRest1().get(0);
        deactivated.setActive(false);
        return deactivated;
    }

    public static List<Dish> getAllFromRest1() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH_1.id, troika, "Salad", 400));
        expectedList.add(new Dish(DISH_2.id, troika, "Soup", 530));
        expectedList.add(new Dish(DISH_3.id, troika, "Meat", 750));
        expectedList.add(new Dish(DISH_4.id, troika, "Fish", 960));
        expectedList.add(new Dish(DISH_5.id, troika, "Juice", 200));
        return expectedList;
    }

    public static List<Dish> getAllFromRest2() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH_6.id, gloria, "Salad", 320));
        expectedList.add(new Dish(DISH_7.id, gloria, "Soup", 400));
        expectedList.add(new Dish(DISH_8.id, gloria, "Meat", 550));
        expectedList.add(new Dish(DISH_9.id, gloria, "Fish", 650));
        expectedList.add(new Dish(DISH_10.id, gloria, "Juice", 120));
        return expectedList;
    }

    public static List<Dish> getAllFromRest1Sorted() {
        List<Dish> expectedList = getAllFromRest1();
        expectedList.sort(Comparator.comparing(Dish::getName));
        return expectedList;
    }
}

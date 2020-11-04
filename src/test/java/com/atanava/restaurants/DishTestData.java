package com.atanava.restaurants;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atanava.restaurants.DbSequence.*;

public class DishTestData {

    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsComparator("restaurant", "menus");

    public static final Restaurant gloria =  new Restaurant(RESTAURANT1_ID.value, "Gloria");

    public static Dish getNew() {
        return new Dish(null, gloria, "New", 800);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(getAllSorted().get(0));
        updated.setName("UpdatedName");
        updated.setId(NEW_ID.value);
        return updated;
    }

    public static List<Dish> getAll() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH1_ID.value, gloria, "Salad", 400));
        expectedList.add(new Dish(DISH2_ID.value, gloria, "Soup", 530));
        expectedList.add(new Dish(DISH3_ID.value, gloria, "Meat", 750));
        expectedList.add(new Dish(DISH4_ID.value, gloria, "Fish", 960));
        expectedList.add(new Dish(DISH5_ID.value, gloria, "Juice", 200));
        return expectedList;
    }

    public static List<Dish> getAllSorted() {
        List<Dish> expectedList = getAll();
        expectedList.sort(Comparator.comparing(Dish::getName));
        return expectedList;
    }
}

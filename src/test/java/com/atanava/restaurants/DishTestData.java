package com.atanava.restaurants;

import com.atanava.restaurants.model.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atanava.restaurants.DbSequence.*;

public class DishTestData {

    public static final Dish expectedDish1 = new Dish(DISH1_ID.value ,"Salad");
    public static final Dish expectedDish2 = new Dish(DISH2_ID.value ,"Soup");
    public static final Dish expectedDish3 = new Dish(DISH3_ID.value ,"Meat");
    public static final Dish expectedDish4 = new Dish(DISH4_ID.value ,"Fish");
    public static final Dish expectedDish5 = new Dish(DISH5_ID.value ,"Juice");

    public static Dish getNew() {
        return new Dish(null, "New");
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(expectedDish1);
        updated.setName("UpdatedName");
        return updated;
    }

    public static List<Dish> getSorted() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(expectedDish1);
        expectedList.add(expectedDish2);
        expectedList.add(expectedDish3);
        expectedList.add(expectedDish4);
        expectedList.add(expectedDish5);
        expectedList.sort(Comparator.comparing(Dish::getName));
        return expectedList;
    }
}

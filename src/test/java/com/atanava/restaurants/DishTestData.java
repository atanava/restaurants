package com.atanava.restaurants;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.atanava.restaurants.DbSequence.*;

public class DishTestData {

//    public static final Dish expectedDish1 = new Dish(DISH1_ID.value, new Restaurant(), "Salad", true, true);
//    public static final Dish expectedDish2 = new Dish(DISH2_ID.value, "Soup");
//    public static final Dish expectedDish3 = new Dish(DISH3_ID.value, "Meat");
//    public static final Dish expectedDish4 = new Dish(DISH4_ID.value, "Fish");
//    public static final Dish expectedDish5 = new Dish(DISH5_ID.value, "Juice");

    public static final Restaurant gloria =  new Restaurant(RESTAURANT1_ID.value, "Gloria");

    public static Dish getNew() {
        return new Dish(null, gloria, "New", true, false, 800);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(getSorted().get(0));
        updated.setName("UpdatedName");
        return updated;
    }

    public static List<Dish> getSorted() {
        List<Dish> expectedList = new ArrayList<>();
        expectedList.add(new Dish(DISH1_ID.value, gloria, "Salad", true, true, 400));
        expectedList.add(new Dish(DISH2_ID.value, gloria, "Soup", true, true, 530));
        expectedList.add(new Dish(DISH3_ID.value, gloria, "Meat", true, true, 750));
        expectedList.add(new Dish(DISH4_ID.value, gloria, "Fish", true, true, 960));
        expectedList.add(new Dish(DISH5_ID.value, gloria, "Juice", true, true, 200));
        expectedList.sort(Comparator.comparing(Dish::getName));
        return expectedList;
    }
}

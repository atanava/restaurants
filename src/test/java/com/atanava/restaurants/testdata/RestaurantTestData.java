package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Restaurant;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsComparator("dishes", "menus", "votes");

    public static final Restaurant troika =  new Restaurant(RESTAURANT_1.id, "Troika");
    public static final Restaurant gloria =  new Restaurant(RESTAURANT_2.id, "Gloria");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(gloria);
        updated.setName("UpdatedName");
        return updated;
    }

}

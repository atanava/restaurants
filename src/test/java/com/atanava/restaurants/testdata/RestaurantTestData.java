package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Restaurant;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class,"dishes", "menus", "votes");

    public static final Restaurant rest1 =  new Restaurant(RESTAURANT_1.id, "Troika");
    public static final Restaurant rest2 =  new Restaurant(RESTAURANT_2.id, "Gloria");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getDuplicate() {
        return new Restaurant(null, "Troika");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(rest1);
        updated.setName("UpdatedName");
        return updated;
    }

}

package com.atanava.restaurants.testdata;

import com.atanava.restaurants.model.Vote;

public class VoteTestData {
    public static final Vote vote1 = new Vote(null, null, RestaurantTestData.rest1, UserTestData.user1);
    public static final Vote vote2 = new Vote(null, null, RestaurantTestData.rest2, UserTestData.user2);
}

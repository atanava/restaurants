package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.UserTestData.*;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;

public class VoteTestData {
    public static TestMatcher<Dish> VOTE_MATCHER = TestMatcher.usingFieldsComparator("restaurant", "user");

    public static final Vote vote1 = new Vote(VOTE_1.id, LocalDate.of(2020, 11, 19), rest1, admin);

    private static final Vote vote2 = new Vote(VOTE_2.id, LocalDate.of(2020, 11, 19), rest1, user1);
    private static final Vote vote3 = new Vote(VOTE_3.id, LocalDate.of(2020, 11, 19), rest2, user2);
    private static final Vote vote4 = new Vote(VOTE_4.id, LocalDate.of(2020, 11, 20), rest1, admin);
    private static final Vote vote5 = new Vote(VOTE_5.id, LocalDate.of(2020, 11, 20), rest2, user1);
    private static final Vote vote6 = new Vote(VOTE_6.id, LocalDate.of(2020, 11, 20), rest2, user2);

    public static Set<Vote> getAllExpected() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote5, vote6, vote1, vote2, vote3));
    }

    public static Set<Vote> getAllExpByUser() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote1));
    }

    public static Set<Vote> getAllExpByRest() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote1, vote2));
    }

    public static Set<Vote> getAllExpByDate() {
        return new LinkedHashSet<>(Arrays.asList(vote5, vote6, vote4));
    }

    public static Set<Vote> getAllExpByRestAndDate() {
        return new LinkedHashSet<>(Arrays.asList(vote5, vote6));
    }
}


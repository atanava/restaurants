package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.UserTestData.*;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsComparator("restaurant", "user");

    public static final Vote vote1 = new Vote(VOTE_1.id, admin, rest1, LocalDate.of(2020, 11, 19));

    private static final Vote vote2 = new Vote(VOTE_2.id, user1, rest1, LocalDate.of(2020, 11, 19));
    private static final Vote vote3 = new Vote(VOTE_3.id, user2, rest2, LocalDate.of(2020, 11, 19));
    private static final Vote vote4 = new Vote(VOTE_4.id, admin, rest1, LocalDate.of(2020, 11, 20));
    private static final Vote vote5 = new Vote(VOTE_5.id, user1, rest2, LocalDate.of(2020, 11, 20));
    private static final Vote vote6 = new Vote(VOTE_6.id, user2, rest2, LocalDate.of(2020, 11, 20));

    public static Vote getNew() {
        return new Vote(null, user1, rest1, LocalDate.now());
    }

    public static Vote getUpdated() {
        Vote updated = getNew();
        updated.setId(NEW_ITEM.id);
        updated.setRestaurant(rest2);
        return updated;
    }

    public static Set<Vote> getAllExpected() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote5, vote6, vote1, vote2, vote3));
    }

    public static Set<Vote> getAllExpByUser() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote1));
    }

    public static Set<Vote> getAllExpByRest1() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote1, vote2));
    }

    public static Set<Vote> getAllExpByRest2() {
        return new LinkedHashSet<>(Arrays.asList(vote5, vote6, vote3));
    }

    public static Set<Vote> getAllExpByDate() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote5, vote6));
    }

    public static Set<Vote> getAllExpByRestAndDate() {
        return new LinkedHashSet<>(Arrays.asList(vote5, vote6));
    }
}


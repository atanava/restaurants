package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.util.VoteUtil;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.UserTestData.*;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;

public class VoteTestData {

    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Vote.class, "restaurant", "restaurantId", "user", "userId");
    public static TestMatcher<VoteTo> VOTE_TO_MATCHER = TestMatcher.usingIgnoringFieldsComparator(VoteTo.class);

    //    TODO move dates to TestUtil
    public static final LocalDate date1 = LocalDate.parse("2020-11-19");
    public static final LocalDate date2 = LocalDate.parse("2020-11-20");
    public static final LocalDate today = LocalDate.now();

    public static final Vote vote1 = new Vote(VOTE_1.id, admin, rest1, date1);

    private static final Vote vote2 = new Vote(VOTE_2.id, user1, rest1, date1);
    private static final Vote vote3 = new Vote(VOTE_3.id, user2, rest2, date1);
    private static final Vote vote4 = new Vote(VOTE_4.id, admin, rest1, date2);
    private static final Vote vote5 = new Vote(VOTE_5.id, user1, rest2, date2);
    private static final Vote vote6 = new Vote(VOTE_6.id, user2, rest2, date2);
    private static final Vote vote7 = new Vote(VOTE_7.id, user1, rest1, today);
    private static final Vote vote8 = new Vote(VOTE_8.id, user2, rest2, today);

    public static Vote getNew() {
        return new Vote(null, admin, rest1, today);
    }

    public static VoteTo getTo() {
        return makeTo(vote1);
    }

    public static VoteTo getTodayTo() {
        return makeTo(getNew());
    }

    public static Vote getUpdated() {
        Vote updated = getNew();
        updated.setId(NEW_ITEM.id);
        updated.setRestaurant(rest2);
        return updated;
    }

    public static Set<Vote> getAllExpected() {
        return new LinkedHashSet<>(Arrays.asList(vote7, vote8, vote4, vote5, vote6, vote1, vote2, vote3));
    }

    public static Set<Vote> getAllExpByAdmin() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote1));
    }

    public static Set<Vote> getAllExpByRest1() {
        return new LinkedHashSet<>(Arrays.asList(vote7, vote4, vote1, vote2));
    }

    public static Set<Vote> getAllExpByRest2() {
        return new LinkedHashSet<>(Arrays.asList(vote8, vote5, vote6, vote3));
    }

    public static Set<Vote> getAllExpByDate() {
        return new LinkedHashSet<>(Arrays.asList(vote4, vote5, vote6));
    }

    public static Set<Vote> getAllExpByRestAndDate() {
        return new LinkedHashSet<>(Arrays.asList(vote5, vote6));
    }

    public static Set<Vote> getAllExpByAdminAndRest() {
        return new LinkedHashSet<>(Arrays.asList(vote1, vote4));
    }

    public static Collection<VoteTo> getExpTos(Set<Vote> votes) {
        votes.forEach(vote -> {
            vote.setUserId(vote.getUser().id());
            vote.setRestaurantId(vote.getRestaurant().id());
        });
        return VoteUtil.createTosFromVotes(votes, new LinkedHashSet<>());
    }

    private static VoteTo makeTo(Vote vote) {
        return new VoteTo(vote.getId(), vote.getUser().id(), vote.getRestaurant().id(), vote.getDate());
    }
}


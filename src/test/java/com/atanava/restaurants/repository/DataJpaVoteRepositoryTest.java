package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.repository.vote.VoteRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.VoteTestData.*;
import static org.junit.Assert.*;

public class DataJpaVoteRepositoryTest extends AbstractTest {

    @Autowired
    VoteRepository voteRepository;

    @Test
    public void save() {
        Vote saved = voteRepository.save(getNew(), USER_1.id, RESTAURANT_1.id);
        int newId = saved.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        Vote actualFromDB = voteRepository.get(newId, USER_1.id);
        VOTE_MATCHER.assertMatch(saved, newVote);
        VOTE_MATCHER.assertMatch(actualFromDB, newVote);
    }

    @Test
    public void incorrectDateSave() {
        assertNull(voteRepository.save(vote1, ADMIN.id, RESTAURANT_2.id));
    }

    @Test
    public void update() {
        voteRepository.save(getNew(), USER_1.id, RESTAURANT_1.id);
        Vote saved = voteRepository.getWithUserAndRest(NEW_ITEM.id);
        voteRepository.save(getUpdated(), USER_1.id, RESTAURANT_2.id);
        Vote updated = voteRepository.getWithUserAndRest(NEW_ITEM.id);

        VOTE_MATCHER.assertMatch(saved, getUpdated());
        VOTE_MATCHER.assertMatch(updated, getUpdated());

        assertEquals(saved.getUser(), updated.getUser());
        assertNotEquals(saved.getRestaurant(), updated.getRestaurant());
    }

    @Test
    public void get() {
        Vote actualFromDB = voteRepository.get(VOTE_1.id, ADMIN.id);
        VOTE_MATCHER.assertMatch(vote1, actualFromDB);
    }

    @Test
    public void getNotFound() {
        assertNull(voteRepository.get(NOT_FOUND.id, USER_1.id));
        assertNull(voteRepository.get(VOTE_1.id, USER_2.id));
    }

    @Test
    public void getAll() {
        VOTE_MATCHER.assertMatch(voteRepository.getAll(), getAllExpected());
    }

    @Test
    public void getAllByUser() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByUser(ADMIN.id), getAllExpByUser());
    }

    @Test
    public void getAllByRestaurant() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByRestaurant(RESTAURANT_1.id), getAllExpByRest1());
    }

    @Test
    public void getAllByDate() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByDate(
                LocalDate.of(2020, 11, 20)), getAllExpByDate());
    }

    @Test
    public void getAllByRestAndDate() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByRestAndDate(
                RESTAURANT_2.id, LocalDate.of(2020, 11, 20)), getAllExpByRestAndDate());
    }

    @Test
    public void getAllByUserAndRest() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByUserAndRest(ADMIN.id, RESTAURANT_1.id), getAllExpByUserAndRest());
    }

    @Test
    public void delete() {
        assertTrue(voteRepository.delete(VOTE_1.id, ADMIN.id));
        assertNull(voteRepository.get(VOTE_1.id, ADMIN.id));
    }
}
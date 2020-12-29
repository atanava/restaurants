package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.repository.vote.VoteRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.VoteTestData.*;

public class DataJpaVoteRepositoryTest extends AbstractTest {

    @Autowired
    VoteRepository voteRepository;

    @Test
    void save() {
        Vote saved = voteRepository.save(getNew(), ADMIN.id, RESTAURANT_1.id);
        int newId = saved.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        Vote actualFromDB = voteRepository.get(newId, ADMIN.id);
        VOTE_MATCHER.assertMatch(saved, newVote);
        VOTE_MATCHER.assertMatch(actualFromDB, newVote);
    }

    @Test
    void incorrectDateSave() {
        assertNull(voteRepository.save(vote1, ADMIN.id, RESTAURANT_2.id));
    }

    @Test
    void update() {
        voteRepository.save(getNew(), ADMIN.id, RESTAURANT_1.id);
        Vote saved = voteRepository.getById(NEW_ITEM.id);
        voteRepository.save(getUpdated(), ADMIN.id, RESTAURANT_2.id);
        Vote updated = voteRepository.getById(NEW_ITEM.id);

        VOTE_MATCHER.assertMatch(saved, getUpdated());
        VOTE_MATCHER.assertMatch(updated, getUpdated());

        assertEquals(saved.getUserId(), updated.getUserId());
        assertNotEquals(saved.getRestaurantId(), updated.getRestaurantId());
    }

    @Test
    void get() {
        Vote actualFromDB = voteRepository.get(VOTE_1.id, ADMIN.id);
        VOTE_MATCHER.assertMatch(vote1, actualFromDB);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> voteRepository.get(NOT_FOUND.id, USER_1.id));
        assertThrows(NotFoundException.class, () -> voteRepository.get(VOTE_1.id, USER_2.id));
    }

    @Test
    void getByUserAndDate() {
        VOTE_MATCHER.assertMatch(voteRepository.getByUserAndDate(ADMIN.id, LocalDate.of(2020, 11, 19)), vote1);
    }

    @Test
    void getByUserAndDateNotFound() {
        assertNull(voteRepository.getByUserAndDate(ADMIN.id, LocalDate.now()));
    }

    @Test
    void getAll() {
        VOTE_MATCHER.assertMatch(voteRepository.getAll(), getAllExpected());
    }

    @Test
    void getAllByUser() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByUser(ADMIN.id), getAllExpByAdmin());
    }

    @Test
    void getAllByRestaurant() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByRestaurant(RESTAURANT_1.id), getAllExpByRest1());
    }

    @Test
    void getAllByDate() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByDate(
                LocalDate.of(2020, 11, 20)), getAllExpByDate());
    }

    @Test
    void getAllByRestAndDate() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByRestAndDate(
                RESTAURANT_2.id, LocalDate.of(2020, 11, 20)), getAllExpByRestAndDate());
    }

    @Test
    void getAllByUserAndRest() {
        VOTE_MATCHER.assertMatch(voteRepository.getAllByUserAndRest(ADMIN.id, RESTAURANT_1.id), getAllExpByAdminAndRest());
    }

    @Test
    void delete() {
        assertTrue(voteRepository.delete(VOTE_1.id));
        assertThrows(NotFoundException.class, () -> voteRepository.get(VOTE_1.id, ADMIN.id));
    }

    @Test
    void deleteNotFound() {
        assertFalse(voteRepository.delete(NEW_ITEM.id));
    }

    @Test
    void deleteAllByRestaurant() {
        voteRepository.deleteAllByRestaurant(RESTAURANT_2.id);
        VOTE_MATCHER.assertMatch(voteRepository.getAllByRestaurant(RESTAURANT_1.id), getAllExpByRest1());
        assertTrue(voteRepository.getAllByRestaurant(RESTAURANT_2.id).isEmpty());
    }

    @Test
    void deleteByUserAndDate() {
        assertTrue(voteRepository.deleteByUserAndDate(ADMIN.id, LocalDate.of(2020, 11, 19)));
    }

    @Test
    void deleteByUserAndDateNotFound() {
        assertFalse(voteRepository.deleteByUserAndDate(ADMIN.id, LocalDate.now()));
    }
}
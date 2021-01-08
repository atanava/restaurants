package com.atanava.restaurants.service;

import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.LinkedHashSet;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService service;

    @Test
    void create() {
        VoteTo created = service.createOrUpdate(ADMIN.id, RESTAURANT_1.id);
        int createdId = created.id();
        VoteTo expected = getTodayTo();
        expected.setId(createdId);
        VoteTo actual = service.get(createdId, ADMIN.id);

        assertEquals(expected.id(), created.id());
        assertEquals(expected.getDate(), created.getDate());
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        VoteService.setExpirationTime(LocalTime.MAX);
        int createdId = service.createOrUpdate(ADMIN.id, RESTAURANT_2.id).id();
        int updatedId = service.createOrUpdate(ADMIN.id, RESTAURANT_1.id).id();
        VoteTo expected = getTodayTo();
        expected.setId(createdId);
        VoteTo actual = service.get(updatedId, ADMIN.id);

        assertEquals(createdId, updatedId);
        assertEquals(expected, actual);
    }

    @Test
    void updateTimeExpired() {
        VoteService.setExpirationTime(LocalTime.MIN);
        service.createOrUpdate(ADMIN.id, RESTAURANT_1.id);
        assertThrows(UnsupportedOperationException.class, () -> service.createOrUpdate(ADMIN.id, RESTAURANT_2.id));
    }

    @Test
    void get() {
        VoteTo actual = service.get(VOTE_1.id, ADMIN.id);
        assertEquals(getTo(), actual);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND.id, USER_1.id));
        assertThrows(NotFoundException.class, () -> service.get(VOTE_1.id, USER_2.id));
    }

    @Test
    void getById() {
        VoteTo actual = service.getById(VOTE_1.id);
        assertEquals(getTo(), actual);
    }

    @Test
    void getByIdNotFound() {
        assertThrows(NotFoundException.class, () -> service.getById(NOT_FOUND.id));
    }

    @Test
    void getAll() {
        VOTE_TO_MATCHER.assertMatch(service.getAll(new LinkedHashSet<>()), getExpTos(getAllExpected()));
    }

    @Test
    void getAllByUser() {
        VOTE_TO_MATCHER.assertMatch(service.getAllByUser(ADMIN.id, new LinkedHashSet<>()), getExpTos(getAllExpByAdmin()));
    }

    @Test
    void getAllByRestaurant() {
        VOTE_TO_MATCHER.assertMatch(service.getAllByRestaurant(RESTAURANT_1.id, new LinkedHashSet<>()), getExpTos(getAllExpByRest1()));
    }

    @Test
    void getAllByDate() {
        VOTE_TO_MATCHER.assertMatch(service.getAllByDate(date2, new LinkedHashSet<>()), getExpTos(getAllExpByDate()));
    }

    @Test
    void getAllByRestAndDate() {
        VOTE_TO_MATCHER.assertMatch(service.getAllByRestAndDate(RESTAURANT_2.id, date2, new LinkedHashSet<>()),
                getExpTos(getAllExpByRestAndDate()));
    }

    @Test
    void getAllByUserAndRest() {
        VOTE_TO_MATCHER.assertMatch(service.getAllByUserAndRest(ADMIN.id, RESTAURANT_1.id, new LinkedHashSet<>()),
                getExpTos(getAllExpByAdminAndRest()));
    }

    @Test
    void delete() {
        service.delete(VOTE_1.id);
        assertThrows(NotFoundException.class, () -> service.get(VOTE_1.id, ADMIN.id));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND.id));
    }

    @Test
    void deleteByUserByToday() {
        VoteService.setExpirationTime(LocalTime.MAX);
        service.deleteByUserByToday(USER_1.id);
        assertThrows(NotFoundException.class, () -> service.get(VOTE_7.id, USER_1.id));
    }

    @Test
    void deleteByUserByTodayTimeExpired() {
        VoteService.setExpirationTime(LocalTime.MIN);
        assertThrows(UnsupportedOperationException.class, () -> service.deleteByUserByToday(USER_1.id));
    }

    @Test
    void deleteByUserByTodayNotFound() {
        VoteService.setExpirationTime(LocalTime.MAX);
        assertThrows(NotFoundException.class, () -> service.deleteByUserByToday(ADMIN.id));
    }

}
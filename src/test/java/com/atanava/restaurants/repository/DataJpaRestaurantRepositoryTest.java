package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.restaurant.RestaurantRepository;
import com.atanava.restaurants.repository.vote.VoteRepository;
import com.atanava.restaurants.testdata.MenuTestData;
import com.atanava.restaurants.testdata.VoteTestData;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;
import static com.atanava.restaurants.TestUtil.convertToSortedArray;

public class DataJpaRestaurantRepositoryTest extends AbstractTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    protected JpaUtil jpaUtil;

    @BeforeEach
    void setUp() {
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void save() {
        Restaurant saved = restaurantRepository.save(getNew());
        int newId = saved.id();
        Restaurant newRest = getNew();
        newRest.setId(newId);
        RESTAURANT_MATCHER.assertMatch(saved, newRest);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(newId), newRest);
    }

    @Test
    void duplicateNameSave() {
        assertThrows(DataAccessException.class, () -> restaurantRepository.save(getDuplicate()));
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        restaurantRepository.save(updated);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(RESTAURANT_1.id), getUpdated());
    }

    @Test
    void delete() {
        assertTrue(restaurantRepository.delete(RESTAURANT_1.id));
        assertThrows(NotFoundException.class, () -> restaurantRepository.get(RESTAURANT_1.id));
    }

    @Test
    void deletedNotFound() {
        assertFalse(restaurantRepository.delete(NOT_FOUND.id));
    }

    @Test
    void get() {
        Restaurant restaurant = restaurantRepository.get(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> restaurantRepository.get(NOT_FOUND.id));
    }

    @Test
    void getAll() {
        List<Restaurant> all = restaurantRepository.getAll();
        RESTAURANT_MATCHER.assertMatch(all, rest2, rest1);
    }

    @Test
    void getAllWithVotes() {
        List<Restaurant> allFromDB = restaurantRepository.getAllWithVotes();
        RESTAURANT_MATCHER.assertMatch(allFromDB, rest2, rest1);

        assertArrayEquals(convertToSortedArray(VoteTestData.getAllExpByRest2()),
                convertToSortedArray(allFromDB.get(0).getVotes()));

        assertArrayEquals(convertToSortedArray(VoteTestData.getAllExpByRest1()),
                convertToSortedArray(allFromDB.get(1).getVotes()));
    }

    @Test
    void getWithVotes() {
        Restaurant restaurant = restaurantRepository.getWithVotes(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);

        assertArrayEquals(convertToSortedArray(VoteTestData.getAllExpByRest1()),
                convertToSortedArray(restaurant.getVotes()));
    }

    @Test
    void getWithMenus() {
        Restaurant restaurant = restaurantRepository.getWithMenus(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);
        assertArrayEquals(convertToSortedArray(MenuTestData.getAllExpByRest1()),
                convertToSortedArray(restaurant.getMenus()));
    }

    @Test
    void getWithVotesAndMenus() {
        Restaurant restaurant = restaurantRepository.getWithVotesAndMenus(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);

        assertArrayEquals(convertToSortedArray(VoteTestData.getAllExpByRest1()),
                convertToSortedArray(restaurant.getVotes()));

        assertArrayEquals(convertToSortedArray(MenuTestData.getAllExpByRest1()),
                convertToSortedArray(restaurant.getMenus()));
    }

}
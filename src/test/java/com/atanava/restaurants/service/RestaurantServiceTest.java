package com.atanava.restaurants.service;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.JpaUtil;
import com.atanava.restaurants.testdata.MenuTestData;
import com.atanava.restaurants.testdata.VoteTestData;
import com.atanava.restaurants.util.RestaurantUtil;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.atanava.restaurants.testdata.DbSequence.NOT_FOUND;
import static com.atanava.restaurants.testdata.DbSequence.RESTAURANT_1;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;
import static com.atanava.restaurants.TestUtil.convertToSortedArray;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    RestaurantService service;

    @Autowired
    protected JpaUtil jpaUtil;

    @BeforeEach
    void setUp() {
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    void create() {
        Restaurant created = service.create(getNew());
        int newId = created.id();
        Restaurant newRest = getNew();
        newRest.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRest);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRest);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () -> service.create(getDuplicate()));
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT_1.id), getUpdated());
    }

    @Test
    void updateNotFound() {
        assertThrows(NotFoundException.class, () -> service.update(new Restaurant(NOT_FOUND.id, "Not exist")));
    }

    @Test
    void delete() {
        service.delete(RESTAURANT_1.id);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_1.id));
    }

    @Test
    void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND.id));
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND.id));
    }

    @Test
    void getTo() {
        RestaurantTo actual = service.getTo(RESTAURANT_1.id, LocalDate.now());
        REST_TO_MATCHER.assertMatch(actual, getRestTo());
    }

    @Test
    void getToNotFound() {
        assertThrows(NotFoundException.class, () -> service.getTo(NOT_FOUND.id, LocalDate.now()));
    }

    @Test
    void getToWithNullMenu() {
        assertNull(service.getTo(RESTAURANT_1.id, LocalDate.now().minusDays(1)).getTodayMenuTo());
    }

    @Test
    void getAll() {
        List<Restaurant> all = service.getAll();
        RESTAURANT_MATCHER.assertMatch(all, rest2, rest1);
    }

    @Test
    void getAllTosWithoutMenus() {
        List<RestaurantTo> allTos = Stream.of(rest2, rest1)
                .map(r -> RestaurantUtil.createToFromRestaurant(r, null))
                .collect(Collectors.toList());

        REST_TO_WITHOUT_MENUS_MATCHER.assertMatch(allTos, service.getAllTosWithoutMenus());
    }

    @Test
    void getAllWithVotes() {
        List<Restaurant> all = service.getAllWithVotes();
        RESTAURANT_MATCHER.assertMatch(all, rest2, rest1);

        rest1.setVotes(VoteTestData.getAllExpByRest1());
        rest2.setVotes(VoteTestData.getAllExpByRest2());
        List<Restaurant> allExpected = List.of(rest2, rest1);

        for (int i = 0; i < allExpected.size(); i++) {
            assertArrayEquals(convertToSortedArray(all.get(i).getVotes()),
                    convertToSortedArray(allExpected.get(i).getVotes()));
        }
    }

    @Test
    void getWithMenus() {
        Restaurant restaurant = service.getWithMenus(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);
        assertArrayEquals(convertToSortedArray(restaurant.getMenus()),
                convertToSortedArray(MenuTestData.getAllExpByRest1()));
    }


    @Test
    void getWithVotes() {
        Restaurant restaurant = service.getWithVotes(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);
        assertArrayEquals(convertToSortedArray(restaurant.getVotes()),
                convertToSortedArray(VoteTestData.getAllExpByRest1()));
    }


    @Test
    void getWithVotesAndMenus() {
        Restaurant restaurant = service.getWithVotesAndMenus(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);

        assertArrayEquals(convertToSortedArray(restaurant.getVotes()),
                convertToSortedArray(VoteTestData.getAllExpByRest1()));

        assertArrayEquals(convertToSortedArray(restaurant.getMenus()),
                convertToSortedArray(MenuTestData.getAllExpByRest1()));

    }
}
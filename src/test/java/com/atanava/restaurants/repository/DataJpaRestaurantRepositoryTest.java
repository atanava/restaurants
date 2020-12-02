package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.restaurant.RestaurantRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;

public class DataJpaRestaurantRepositoryTest extends AbstractTest {

    @Autowired
    RestaurantRepository repository;

    @Test
    public void save() {
        Restaurant saved = repository.save(getNew());
        int newId = saved.id();
        Restaurant newRest = getNew();
        newRest.setId(newId);
        RESTAURANT_MATCHER.assertMatch(saved, newRest);
        RESTAURANT_MATCHER.assertMatch(repository.get(newId), newRest);
    }

    @Test
    public void duplicateNameSave() {
        assertThrows(DataAccessException.class, () -> repository.save(getDuplicate()));
    }

    @Test
    public void update() {
        Restaurant updated = getUpdated();
        repository.save(updated);
        RESTAURANT_MATCHER.assertMatch(repository.get(RESTAURANT_1.id), getUpdated());
    }

    @Test
    public void delete() {
        repository.delete(RESTAURANT_1.id);
        assertNull(repository.get(RESTAURANT_1.id));
    }

    @Test
    public void deletedNotFound() {
        assertFalse(repository.delete(NOT_FOUND.id));
    }

    @Test
    public void get() {
        Restaurant restaurant = repository.get(RESTAURANT_1.id);
        RESTAURANT_MATCHER.assertMatch(restaurant, rest1);
    }

    @Test
    public void getNotFound() {
        assertNull(repository.get(NOT_FOUND.id));
    }

    @Test
    public void getAll() {
        List<Restaurant> all = repository.getAll();
        RESTAURANT_MATCHER.assertMatch(all, rest2, rest1);
    }
}
package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.repository.dish.DishRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.DishTestData.*;

public class DataJpaDishRepositoryTest extends AbstractTest {

    @Autowired
    DishRepository repository;

    @Test
    void save() {
        Dish saved = repository.save(getNew(), RESTAURANT_1.id);
        int newId = saved.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(saved, newDish);
        DISH_MATCHER.assertMatch(repository.get(newId, RESTAURANT_1.id), newDish);
    }

    @Test
    void duplicateSave() {
        assertThrows(DataAccessException.class, () -> repository.save(getDuplicate(), RESTAURANT_1.id));
    }

    @Test
    void delete() {
        assertThrows(UnsupportedOperationException.class, () -> repository.delete(DISH_1.id, RESTAURANT_1.id));
    }

    @Test
    void deactivate() {
        assertTrue(repository.deactivate(DISH_1.id, RESTAURANT_1.id));
        DISH_MATCHER.assertMatch(repository.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
    }

    @Test
    void deactivatedNotFound() {
        assertThrows(NotFoundException.class, () -> repository.deactivate(NEW_ITEM.id, RESTAURANT_1.id));
    }

    @Test
    void activate() {
        repository.deactivate(DISH_1.id, RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(repository.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
        assertTrue(repository.activate(DISH_1.id, RESTAURANT_1.id));
        DISH_MATCHER.assertMatch(repository.get(DISH_1.id, RESTAURANT_1.id), getAllFromRest1().get(0));
    }

    @Test
    void activateNotFound() {
        assertThrows(NotFoundException.class, () -> repository.activate(NEW_ITEM.id, RESTAURANT_1.id));
    }

    @Test
    void get() {
        Dish dish = repository.get(DISH_1.id, RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(dish, getAllFromRest1().get(0));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> repository.get(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    void getAll() {
        List<Dish> all = repository.getAll(RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(all, getAllFromRest1Sorted());
    }

    @Test
    void getByActive() {
        repository.deactivate(DISH_4.id, RESTAURANT_1.id);
        List<Dish> dishes =  getAllFromRest1Sorted();
        Dish deactivated = dishes.remove(0);
        deactivated.setActive(false);
        DISH_MATCHER.assertMatch(repository.getByActive(RESTAURANT_1.id, true), dishes);
        DISH_MATCHER.assertMatch(repository.getByActive(RESTAURANT_1.id, false).get(0), deactivated);
    }

}
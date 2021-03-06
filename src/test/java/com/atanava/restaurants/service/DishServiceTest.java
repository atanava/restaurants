package com.atanava.restaurants.service;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.DishTestData.*;

class DishServiceTest extends AbstractServiceTest {

    @Autowired
    DishService service;

    @Test
    void create() {
        Dish created = service.create(getNew(), RESTAURANT_1.id);
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(service.get(newId, RESTAURANT_1.id), newDish);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () -> service.create(getDuplicate(), RESTAURANT_1.id));
    }

    @Test
    void activate() {
        assertTrue(service.activate(DISH_1.id, RESTAURANT_1.id, false));
        DISH_MATCHER.assertMatch(service.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
        assertTrue(service.activate(DISH_1.id, RESTAURANT_1.id, true));
        DISH_MATCHER.assertMatch(service.get(DISH_1.id, RESTAURANT_1.id), getAllFromRest1().get(0));
    }

    @Test
    void activateNotFound() {
        assertThrows(NotFoundException.class, () -> service.activate(NEW_ITEM.id, RESTAURANT_1.id, true));
    }

    @Test
    void get() {
        Dish dish = service.get(DISH_1.id, RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(dish, getAllFromRest1().get(0));
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    void getAll() {
        List<Dish> all = service.getAll(RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(all, getAllFromRest1Sorted());
    }

    @Test
    void getByActive() {
        service.activate(DISH_4.id, RESTAURANT_1.id, false);
        List<Dish> dishes = getAllFromRest1Sorted();
        Dish deactivated = dishes.remove(0);
        deactivated.setActive(false);
        DISH_MATCHER.assertMatch(service.getByActive(RESTAURANT_1.id, true), dishes);
        DISH_MATCHER.assertMatch(service.getByActive(RESTAURANT_1.id, false).get(0), deactivated);
    }
}
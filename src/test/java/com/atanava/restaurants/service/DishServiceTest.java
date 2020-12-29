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
        Dish saved = service.create(getNew(), RESTAURANT_1.id);
        int newId = saved.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(saved, newDish);
        DISH_MATCHER.assertMatch(service.get(newId, RESTAURANT_1.id), newDish);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT_1.id);
        updated = getUpdated();
        updated.setId(NEW_ITEM.id);
        DISH_MATCHER.assertMatch(service.get(NEW_ITEM.id, RESTAURANT_1.id), updated);
        DISH_MATCHER.assertMatch(service.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () -> service.create(getDuplicate(), RESTAURANT_1.id));
    }


    @Test
    void deactivate() {
        assertTrue(service.deactivate(DISH_1.id, RESTAURANT_1.id));
        DISH_MATCHER.assertMatch(service.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
    }

    @Test
    void deactivatedNotFound() {
        assertThrows(NotFoundException.class, () -> service.deactivate(NEW_ITEM.id, RESTAURANT_1.id));
    }

    @Test
    void activate() {
        service.deactivate(DISH_1.id, RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(service.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
        assertTrue(service.activate(DISH_1.id, RESTAURANT_1.id));
        DISH_MATCHER.assertMatch(service.get(DISH_1.id, RESTAURANT_1.id), getAllFromRest1().get(0));
    }

    @Test
    void activateNotFound() {
        assertThrows(NotFoundException.class, () -> service.activate(NEW_ITEM.id, RESTAURANT_1.id));
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
        service.deactivate(DISH_4.id, RESTAURANT_1.id);
        List<Dish> dishes =  getAllFromRest1Sorted();
        Dish deactivated = dishes.remove(0);
        deactivated.setActive(false);
        DISH_MATCHER.assertMatch(service.getByActive(RESTAURANT_1.id, true), dishes);
        DISH_MATCHER.assertMatch(service.getByActive(RESTAURANT_1.id, false).get(0), deactivated);
    }
}
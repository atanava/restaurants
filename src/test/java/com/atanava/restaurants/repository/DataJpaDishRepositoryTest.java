package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Dish;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;
import static com.atanava.restaurants.DbSequence.*;
import static com.atanava.restaurants.DishTestData.*;

public class DataJpaDishRepositoryTest extends AbstractTest {

    @Autowired
    DishRepository repository;

    @Test
    public void save() {
        Dish saved = repository.save(getNew(), RESTAURANT1_ID.value);
        int newId = saved.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(saved, newDish);
        DISH_MATCHER.assertMatch(repository.get(newId, RESTAURANT1_ID.value), newDish);
    }

    @Test
    public void duplicateSaved() throws Exception {
        assertThrows(DataAccessException.class, () -> repository.save(new Dish(
                null, gloria, "Salad", 300), RESTAURANT1_ID.value));
    }

    @Test
    public void delete() {
        assertThrows(UnsupportedOperationException.class, () -> repository.delete(DISH1_ID.value, RESTAURANT1_ID.value));
    }

    @Test
    public void deactivate() {
        repository.deactivate(DISH1_ID.value, RESTAURANT1_ID.value);
        DISH_MATCHER.assertMatch(repository.get(DISH1_ID.value, RESTAURANT1_ID.value), getDeactivated());
    }

    @Test
    public void deactivatedNotFound() {
        assertNull(repository.deactivate(NEW_ID.value, RESTAURANT1_ID.value));
    }

    @Test
    public void get() {
        Dish dish = repository.get(DISH1_ID.value, RESTAURANT1_ID.value);
        DISH_MATCHER.assertMatch(dish, getAllExpected().get(0));
    }

    @Test
    public void getNotFound() throws Exception {
        assertNull(repository.get(NOT_FOUND.value, RESTAURANT1_ID.value));
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        repository.save(updated, RESTAURANT1_ID.value);
        updated = getUpdated();
        updated.setId(NEW_ID.value);
        DISH_MATCHER.assertMatch(repository.get(NEW_ID.value, RESTAURANT1_ID.value), updated);
        DISH_MATCHER.assertMatch(repository.get(DISH1_ID.value, RESTAURANT1_ID.value), getDeactivated());
    }

    @Test
    public void getAll() {
        List<Dish> all = repository.getAll(RESTAURANT1_ID.value);
        DISH_MATCHER.assertMatch(all, getAllSorted());
    }
}
package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.repository.dish.DishRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.Assert.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.DishTestData.*;

public class DataJpaDishRepositoryTest extends AbstractTest {

    @Autowired
    DishRepository repository;

    @Test
    public void save() {
        Dish saved = repository.save(getNew(), RESTAURANT_1.id);
        int newId = saved.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(saved, newDish);
        DISH_MATCHER.assertMatch(repository.get(newId, RESTAURANT_1.id), newDish);
    }

    @Test
    public void duplicateNameSave() throws Exception {
        assertThrows(DataAccessException.class, () -> repository.save(getDuplicate(), RESTAURANT_1.id));
    }

    @Test
    public void delete() {
        assertThrows(UnsupportedOperationException.class, () -> repository.delete(DISH_1.id, RESTAURANT_1.id));
    }

    @Test
    public void deactivate() {
        repository.deactivate(DISH_1.id, RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(repository.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
    }

    @Test
    public void deactivatedNotFound() {
        assertNull(repository.deactivate(NEW_ITEM.id, RESTAURANT_1.id));
    }

    @Test
    public void get() {
        Dish dish = repository.get(DISH_1.id, RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(dish, getAllExpected().get(0));
    }

    @Test
    public void getNotFound() throws Exception {
        assertNull(repository.get(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        repository.save(updated, RESTAURANT_1.id);
        updated = getUpdated();
        updated.setId(NEW_ITEM.id);
        DISH_MATCHER.assertMatch(repository.get(NEW_ITEM.id, RESTAURANT_1.id), updated);
        DISH_MATCHER.assertMatch(repository.get(DISH_1.id, RESTAURANT_1.id), getDeactivated());
    }

    @Test
    public void getAll() {
        List<Dish> all = repository.getAll(RESTAURANT_1.id);
        DISH_MATCHER.assertMatch(all, getAllSorted());
    }
}
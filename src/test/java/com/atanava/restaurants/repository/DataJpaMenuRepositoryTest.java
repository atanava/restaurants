package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.repository.menu.MenuRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.MenuTestData.*;

public class DataJpaMenuRepositoryTest extends AbstractTest {

    @Autowired
    MenuRepository repository;

    @Test
    public void save() {
        Menu saved = repository.save(getNew(), RESTAURANT_1.id);
        int newId = saved.id();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(saved, newMenu);
        MENU_MATCHER.assertMatch(repository.get(newId, RESTAURANT_1.id), newMenu);
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getAllByRestaurant() {
    }

    @Test
    public void getAllByDate() {
    }
}
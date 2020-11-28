package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.repository.menu.MenuRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
        Menu actualFromDB = repository.get(newId, RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(saved, newMenu);
        MENU_MATCHER.assertMatch(actualFromDB, newMenu);

        //https://stackoverflow.com/questions/55621145/how-to-work-with-hibernates-persistentbag-not-obeying-list-equals-contract
        assertTrue(Objects.deepEquals(
              actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                newMenu.getDishes().toArray()
        ));
    }

    @Test
    public void duplicateDateSave() {
        assertThrows(DataAccessException.class, () -> repository.save(getDuplicate(), RESTAURANT_1.id));
    }

    @Test
//    @Ignore
    public void update() {
        Menu updated = repository.save(getUpdated(), RESTAURANT_1.id);
        int updatedId = updated.id();
        Menu expected = getUpdated();
        Menu actualFromDB = repository.get(updatedId, RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(updated, expected);
        MENU_MATCHER.assertMatch(actualFromDB, expected);
        assertTrue(Objects.deepEquals(
                actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                expected.getDishes().toArray()
        ));
    }

    @Test
    public void delete() {
        repository.delete(MENU_1.id, RESTAURANT_1.id);
        assertNull(repository.get(MENU_1.id, RESTAURANT_1.id));
    }

    @Test
    public void deleteNotFound() {
        assertFalse(repository.delete(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    public void get() {
        Menu expected = menuOfTroika1;
        Menu actualFromDB = repository.get(MENU_1.id, RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(actualFromDB, expected);
        assertTrue(Objects.deepEquals(
                actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                expected.getDishes().toArray()
        ));

    }

    @Test
    public void getNotFound() {
        assertNull(repository.get(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    public void getAll() {
        List<Menu> all = repository.getAll();
        MENU_MATCHER.assertMatch(all, getAllExpected());
    }

    @Test
    public void getAllByRestaurant() {
        List<Menu> allByRestaurant = repository.getAllByRestaurant(RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(allByRestaurant, getAllExpByRestaurant());
    }

    @Test
    public void getAllByDate() {
        List<Menu> allByDate = repository.getAllByDate(date1);
        MENU_MATCHER.assertMatch(allByDate, getAllExpByDate());
    }
}
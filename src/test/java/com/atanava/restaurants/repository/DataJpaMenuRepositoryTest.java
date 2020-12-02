package com.atanava.restaurants.repository;

import com.atanava.restaurants.AbstractTest;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.repository.dish.DishRepository;
import com.atanava.restaurants.repository.menu.MenuRepository;
import com.atanava.restaurants.testdata.DishTestData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.MenuTestData.*;

public class DataJpaMenuRepositoryTest extends AbstractTest {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    DishRepository dishRepository;

    @Test
    public void save() {
        Menu saved = menuRepository.save(getNew(), RESTAURANT_1.id);
        int newId = saved.id();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        Menu actualFromDB = menuRepository.get(newId, RESTAURANT_1.id);
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
        assertThrows(DataAccessException.class, () -> menuRepository.save(getDuplicate(), RESTAURANT_1.id));
    }

    @Test
    public void update() {
        Dish newDish = dishRepository.save(DishTestData.getNew(), RESTAURANT_1.id);
        Menu updated = menuRepository.save(getUpdated(), RESTAURANT_1.id);
        int updatedId = updated.id();
        Menu expected = getUpdated();
        Menu actualFromDB = menuRepository.get(updatedId, RESTAURANT_1.id);

        MENU_MATCHER.assertMatch(updated, expected);
        MENU_MATCHER.assertMatch(actualFromDB, expected);

        assertTrue(Objects.deepEquals(
                actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                expected.getDishes().toArray()
        ));

        assertTrue(Objects.deepEquals(actualFromDB.getDishes().get(4), newDish));
    }

    @Test
    public void delete() {
        menuRepository.delete(MENU_1.id, RESTAURANT_1.id);
        assertNull(menuRepository.get(MENU_1.id, RESTAURANT_1.id));
    }

    @Test
    public void deleteNotFound() {
        assertFalse(menuRepository.delete(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    public void get() {
        Menu expected = menuOfTroika1;
        Menu actualFromDB = menuRepository.get(MENU_1.id, RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(actualFromDB, expected);
        assertTrue(Objects.deepEquals(
                actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                expected.getDishes().toArray()
        ));
    }

    @Test
    public void getNotFound() {
        assertNull(menuRepository.get(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    public void getByRestAndDate() {
        Menu actualFromDB = menuRepository.getByRestAndDate(RESTAURANT_1.id, date1);
        MENU_MATCHER.assertMatch(actualFromDB, menuOfTroika1);
    }

    @Test
    public void getByRestAndDateNotFound() {
        assertNull(menuRepository.getByRestAndDate(RESTAURANT_1.id, today));
    }

    @Test
    public void getAll() {
        List<Menu> all = menuRepository.getAll();
        MENU_MATCHER.assertMatch(all, getAllExpected());
    }

    @Test
    public void getAllByRestaurant() {
        List<Menu> allByRestaurant = menuRepository.getAllByRestaurant(RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(allByRestaurant, getAllExpByRestaurant());
    }

    @Test
    public void getAllByDate() {
        List<Menu> allByDate = menuRepository.getAllByDate(date1);
        MENU_MATCHER.assertMatch(allByDate, getAllExpByDate());
    }
}
package com.atanava.restaurants.service;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.repository.dish.DishRepository;
import com.atanava.restaurants.testdata.DishTestData;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.testdata.MenuTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    MenuService service;

    @Autowired
    DishRepository dishRepository;

    @Test
    void create() {
        Menu created = service.create(getNew(), RESTAURANT_2.id);
        int newId = created.id();
        Menu newMenu = getNew();
        newMenu.setId(newId);
        Menu actualFromDB = service.get(newId, RESTAURANT_2.id);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(actualFromDB, newMenu);

        //https://stackoverflow.com/questions/55621145/how-to-work-with-hibernates-persistentbag-not-obeying-list-equals-contract
        assertArrayEquals(actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                newMenu.getDishes().toArray());

    }

    @Test
    void PastDateCreate() {
        Menu created = getNew();
        created.setDate(today.minusDays(1));
        assertThrows(TransactionSystemException.class, () -> service.create(created, RESTAURANT_2.id));
    }

    @Test
    void duplicateDateCreate() {
        service.create(getNew(), RESTAURANT_2.id);
        assertThrows(DataAccessException.class, () -> service.create(getNew(), RESTAURANT_2.id));
    }

    @Test
    void incorrectRestaurantDishesCreate() {
        assertThrows(IllegalArgumentException.class, () -> service.create(getWithWrongDishes(), RESTAURANT_2.id));
    }

    @Test
    void update() {
        Dish newDish = dishRepository.save(DishTestData.getNew(), RESTAURANT_1.id);
        Menu updated = service.create(getUpdated(), RESTAURANT_1.id);
        int updatedId = updated.id();
        Menu expected = getUpdated();
        Menu actualFromDB = service.get(updatedId, RESTAURANT_1.id);

        MENU_MATCHER.assertMatch(updated, expected);
        MENU_MATCHER.assertMatch(actualFromDB, expected);

        assertArrayEquals(actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                expected.getDishes().toArray());

        assertTrue(Objects.deepEquals(actualFromDB.getDishes().get(4), newDish));

    }

    @Test
    void updateNotFound() {
        Menu notExist = new Menu(NOT_FOUND.id, null, Collections.emptyList(), LocalDate.now());
        assertThrows(NotFoundException.class, () -> service.update(notExist, RESTAURANT_1.id));
    }

    @Test
    void incorrectRestaurantDishesUpdate() {
        assertThrows(IllegalArgumentException.class, () -> service.update(getWithWrongDishes(), RESTAURANT_2.id));
    }

    @Test
    void delete() {
        service.delete(MENU_1.id, RESTAURANT_1.id);
        assertThrows(NotFoundException.class, () -> service.get(MENU_1.id, RESTAURANT_1.id));
        assertNotNull(dishRepository.get(DISH_1.id, RESTAURANT_1.id));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    void get() {
        Menu expected = menuOfTroika1;
        Menu actualFromDB = service.get(MENU_1.id, RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(actualFromDB, expected);

        assertArrayEquals(actualFromDB.getDishes() == null ? new Dish[0] : actualFromDB.getDishes().toArray(),
                expected.getDishes().toArray());
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND.id, RESTAURANT_1.id));
    }

    @Test
    void getByRestAndDate() {
        Menu actualFromDB = service.getByRestAndDate(RESTAURANT_1.id, date1);
        MENU_MATCHER.assertMatch(actualFromDB, menuOfTroika1);
    }

    @Test
    public void getByRestAndDateNotFound() {
        assertNull(service.getByRestAndDate(RESTAURANT_1.id, today.minusDays(1)));
    }

    @Test
    void getAll() {
        List<Menu> all = service.getAll();
        MENU_MATCHER.assertMatch(all, getAllExpected());
    }

    @Test
    void getAllByRestaurant() {
        List<Menu> allByRestaurant = service.getAllByRestaurant(RESTAURANT_1.id);
        MENU_MATCHER.assertMatch(allByRestaurant, getAllExpByRest1());
    }

    @Test
    void getAllByDate() {
        List<Menu> allByDate = service.getAllByDate(date1);
        MENU_MATCHER.assertMatch(allByDate, getAllExpByDate());
    }
}
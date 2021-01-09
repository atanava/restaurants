package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class MenuTestData {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Menu.class,"dishes", "restaurant", "restaurantId");

    private static final Restaurant rest1 = RestaurantTestData.rest1;
    private static final Restaurant rest2 = RestaurantTestData.rest2;

    public static final LocalDate date1 = LocalDate.parse("2020-11-19");
    public static final LocalDate date2 = LocalDate.parse("2020-11-20");
    public static final LocalDate today = LocalDate.now();

    public static Menu menuOfTroika1 = new Menu(MENU_1.id, rest1, DishTestData.getAllFromRest1(), date1);
    public static Menu menuOfTroika2 = new Menu(MENU_3.id, rest1, DishTestData.getAllFromRest1(), today);
    public static Menu menuOfGloria1 = new Menu(MENU_2.id, rest2, DishTestData.getAllFromRest2(), date1);


    public static Menu getNew() {
        return new Menu(null, rest2, DishTestData.getAllFromRest2(), today);
    }

    public static Menu getDuplicate() {
        return  new Menu(null, rest1, DishTestData.getAllFromRest1(), date1);
    }

    public static Menu getUpdated() {
        List<Dish> dishes = new ArrayList<>(List.copyOf(menuOfTroika1.getDishes()));
        if ("Salad".equals(dishes.get(0).getName())) {
            dishes.remove(0);
            dishes.add(DishTestData.getNew());
            dishes.get(4).setId(NEW_ITEM.id);
        }
        Menu updated = new Menu(MENU_1.id, rest1, Collections.emptyList(), date1);
        updated.setDishes(dishes);
        return updated;
    }

    public static List<Menu> getAllExpected() {
        return List.of(menuOfTroika2, menuOfTroika1, menuOfGloria1);
    }

    public static List<Menu> getAllExpByRest1() {
        return List.of(menuOfTroika2, menuOfTroika1);
    }

    public static List<Menu> getAllExpByDate() {
        return List.of(menuOfTroika1, menuOfGloria1);
    }
}

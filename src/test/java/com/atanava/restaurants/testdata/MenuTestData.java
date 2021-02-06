package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class MenuTestData {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Menu.class, "dishes", "restaurant");

    public static final LocalDate date1 = LocalDate.parse("2020-11-19");
    public static final LocalDate today = LocalDate.now();

    public static Menu menuOfTroika1 = new Menu(MENU_1.id, null, DishTestData.getAllFromRest1(), date1);
    public static Menu menuOfTroika2 = new Menu(MENU_3.id, null, DishTestData.getAllFromRest1(), today);
    public static Menu menuOfGloria1 = new Menu(MENU_2.id, null, DishTestData.getAllFromRest2(), date1);

    static {
        menuOfTroika1.setRestaurantId(RESTAURANT_1.id);
        menuOfTroika2.setRestaurantId(RESTAURANT_1.id);
        menuOfGloria1.setRestaurantId(RESTAURANT_2.id);
    }


    public static Menu getNew() {
        Menu newMenu = new Menu(null, null, DishTestData.getAllFromRest2(), today);
        newMenu.setRestaurantId(RESTAURANT_2.id);
        return newMenu;
    }

    public static Menu getUpdated() {
        List<Dish> dishes = new ArrayList<>(List.copyOf(menuOfTroika1.getDishes()));
        if ("Salad".equals(dishes.get(0).getName())) {
            dishes.remove(0);
            dishes.add(DishTestData.getNew());
            dishes.get(4).setId(NEW_ITEM.id);
        }
        Menu updated = new Menu(MENU_1.id, null, Collections.emptyList(), date1);
        updated.setDishes(dishes);
        updated.setRestaurantId(RESTAURANT_1.id);
        return updated;
    }

    public static Menu getWithWrongDishes() {
        Menu newMenu = new Menu(null, null, DishTestData.getAllFromRest1(), today);
        newMenu.setRestaurantId(RESTAURANT_2.id);
        return newMenu;
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

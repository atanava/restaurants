package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class MenuTestData {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator("dishes", "restaurant");

    private static final Restaurant troika = RestaurantTestData.troika;
    private static final Restaurant gloria = RestaurantTestData.gloria;

    public static final LocalDate date1 = LocalDate.parse("2020-11-19");
    public static final LocalDate date2 = LocalDate.parse("2020-11-20");
    public static final LocalDate today = LocalDate.now();

    public static Menu menuOfTroika1 = new Menu(MENU_1.id, troika, DishTestData.getAllFromTroika(), date1);
    public static Menu menuOfTroika2 = new Menu(MENU_3.id, troika, DishTestData.getAllFromTroika(), date2);
    public static Menu menuOfGloria = new Menu(MENU_2.id, gloria, DishTestData.getAllFromGloria(), date1);


    public static Menu getNew() {
        return new Menu(troika, DishTestData.getAllFromTroika(), today);
    }

    public static Menu getDuplicate() {
        return  new Menu(troika, DishTestData.getAllFromTroika(), date1);
    }

    public static Menu getUpdated() {
        List<Dish> dishes = new java.util.ArrayList<>(List.copyOf(menuOfTroika1.getDishes()));
        if ("Salad".equals(dishes.get(0).getName())) {
            dishes.remove(0);
            dishes.add(DishTestData.getNew());
            dishes.get(4).setId(NEW_ITEM.id);
        }
        Menu updated = new Menu(MENU_1.id, troika, Collections.emptyList(), date1);
        updated.setDishes(dishes);
        return updated;
    }

    public static List<Menu> getAllExpected() {
        return List.of(menuOfTroika2, menuOfTroika1, menuOfGloria);
    }

    public static List<Menu> getAllExpByRestaurant() {
        return List.of(menuOfTroika2, menuOfTroika1);
    }

    public static List<Menu> getAllExpByDate() {
        return List.of(menuOfTroika1, menuOfGloria);
    }
}

package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

import static com.atanava.restaurants.testdata.DbSequence.*;

public class MenuTestData {

    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingFieldsComparator("dishes", "restaurant");

    private static final Restaurant troika = RestaurantTestData.troika;
    private static final Restaurant gloria = RestaurantTestData.gloria;

    public static final LocalDate date1 = LocalDate.parse("2020-11-19");
    private static final LocalDate date2 = LocalDate.parse("2020-11-20");
    private static final LocalDate today = LocalDate.now();

    public static Menu menuOfTroika1 = new Menu(MENU_1.id, troika, DishTestData.getAllExpected(), date1);
    public static Menu menuOfTroika2 = new Menu(MENU_3.id, troika, DishTestData.getAllExpected(), date2);
    public static Menu menuOfGloria = new Menu(MENU_2.id, gloria, DishTestData.getAllExpected(), date1);


    public static Menu getNew() {
        return new Menu(troika, DishTestData.getAllExpected(), today);
    }

    public static Menu getDuplicate() {
        return  new Menu(troika, DishTestData.getAllExpected(), date1);
    }

    public static Menu getUpdated() {
        Menu updated = menuOfTroika1;
        List<Dish> dishes = updated.getDishes();
        dishes.remove(0);
        dishes.get(0).setPrice(500);
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

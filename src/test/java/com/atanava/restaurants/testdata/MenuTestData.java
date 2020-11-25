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

    private static final LocalDate dateOld = LocalDate.parse("2020-11-19");
    private static final LocalDate  dateNew = LocalDate.parse("2020-11-20");

    public static Menu getNew() {
        return new Menu(troika, DishTestData.getAllExpected(), dateNew);
    }

    public static Menu getDuplicate() {
        return  new Menu(troika, DishTestData.getAllExpected(), dateOld);
    }

    public static Menu getUpdated() {
        Menu updated = getAllExpected().get(0);
        List<Dish> dishes = updated.getDishes();
        dishes.remove(0);
        dishes.get(0).setPrice(500);
        return updated;
    }

    public static List<Menu> getAllExpected() {
        Menu menuOfTroika = new Menu(MENU_1.id, troika, DishTestData.getAllExpected(), dateOld);
        Menu menuOfGloria = new Menu(MENU_2.id, gloria, DishTestData.getAllExpected(), dateOld);
        return List.of(menuOfTroika, menuOfGloria);
    }
    //TODO add menus from one restaurant

}
package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.DishTo;
import com.atanava.restaurants.dto.MenuTo;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static com.atanava.restaurants.util.RestaurantUtil.createRestaurantFromTo;
import static com.atanava.restaurants.util.DishUtil.createDishFromTo;
import static com.atanava.restaurants.util.DishUtil.createToFromDish;

public class MenuUtil {

    public static Menu createMenuFromTo(MenuTo menuTo) {
        if (menuTo == null) {
            return null;
        }

        List<Dish> dishes =  new ArrayList<>();
        for (DishTo dishTo : menuTo.getDishTos()) {
            dishes.add(createDishFromTo(dishTo));
        }
        return new Menu(null, createRestaurantFromTo(menuTo.getRestaurantTo()), dishes, menuTo.getDate());
    }

    public static Menu updateMenuFromTo(Menu menu, MenuTo menuTo) {
        Menu updated = createMenuFromTo(menuTo);
        updated.setId(menu.id());
        return updated;
    }

    public static MenuTo createToFromMenu(Menu menu) {
        if (menu == null) return null;

        List<DishTo> dishTos = new ArrayList<>();
        for (Dish dish : menu.getDishes()) {
            dishTos.add(createToFromDish(dish));
        }
        return new MenuTo(menu.id(), null, dishTos, menu.getDate());
    }


}

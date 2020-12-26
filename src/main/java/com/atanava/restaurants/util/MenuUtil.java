package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.DishTo;
import com.atanava.restaurants.dto.MenuTo;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static com.atanava.restaurants.util.DishUtil.createToFromDish;

public class MenuUtil {

    public static MenuTo createToFromMenu(Menu menu) {
        if (menu == null) return null;

        List<DishTo> dishTos = new ArrayList<>();
        for (Dish dish : menu.getDishes()) {
            dishTos.add(createToFromDish(dish));
        }
        return new MenuTo(menu.id(), menu.getRestaurantId(), dishTos, menu.getDate());
    }


}

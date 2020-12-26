package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.DishTo;
import com.atanava.restaurants.model.Dish;

public class DishUtil {

    public static DishTo createToFromDish(Dish dish) {
        return new DishTo(dish.id(), dish.getName(), dish.getRestaurantId(), dish.getPrice());
    }

}

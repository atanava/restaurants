package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.DishTo;
import com.atanava.restaurants.model.Dish;

import static com.atanava.restaurants.util.RestaurantUtil.createRestaurantFromTo;

public class DishUtil {

    public static Dish createDishFromTo(DishTo dishTo) {
        return new Dish(null, dishTo.getName(), createRestaurantFromTo(dishTo.getRestaurantTo()), dishTo.getPrice());
    }

    public static Dish updateDishFromTo(Dish dish, DishTo dishTo) {
        dish.setName(dishTo.getName());
        dish.setPrice(dishTo.getPrice());
        return dish;
    }

    public static DishTo createToFromDish(Dish dish) {
        return new DishTo(dish.id(), dish.getName(), null, dish.getPrice());
    }

}

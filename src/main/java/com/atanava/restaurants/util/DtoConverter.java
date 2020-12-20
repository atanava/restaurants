package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.*;
import com.atanava.restaurants.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DtoConverter {

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

    //---------Menu----------

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

    //---------Restaurant----------

    public static Restaurant createRestaurantFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(null, restaurantTo.getName());
    }

    public static Restaurant updateRestaurantFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }

    public static RestaurantTo createToFromRestaurant(Restaurant restaurant, Menu menu) {
        return new RestaurantTo(restaurant.id(), restaurant.getName(), createToFromMenu(menu), restaurant.getVotes().size());
    }

    //---------User----------

    public static User createUserFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail(), userTo.getPassword(), Role.USER);
    }

    public static User updateUserFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static UserTo createToFromUser(User user) {
        return new UserTo(user.id(), user.getName(), user.getEmail(), user.getPassword());
    }

    //TODO
    public static Vote createVoteFromTo(VoteTo voteTo) {
        return null;
    }
}

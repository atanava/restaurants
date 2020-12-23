package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;

import static com.atanava.restaurants.util.MenuUtil.createToFromMenu;

public class RestaurantUtil {

    public static Restaurant createRestaurantFromTo(RestaurantTo restaurantTo) {
        return new Restaurant(restaurantTo.id(), restaurantTo.getName());
    }

    public static Restaurant updateRestaurantFromTo(Restaurant restaurant, RestaurantTo restaurantTo) {
        restaurant.setName(restaurantTo.getName());
        return restaurant;
    }

    public static RestaurantTo createToFromRestaurant(Restaurant restaurant, Menu menu) {
        return new RestaurantTo(restaurant.id(), restaurant.getName(), createToFromMenu(menu), restaurant.getVotes().size());
    }

}

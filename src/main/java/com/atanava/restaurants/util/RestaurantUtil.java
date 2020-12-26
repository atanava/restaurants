package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;

import static com.atanava.restaurants.util.MenuUtil.createToFromMenu;

public class RestaurantUtil {

    public static RestaurantTo createToFromRestaurant(Restaurant restaurant, Menu menu) {
        return new RestaurantTo(restaurant.id(), restaurant.getName(), createToFromMenu(menu));
    }

}

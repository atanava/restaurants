package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create restaurant {}", restaurant.getName());
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}",restaurant.getId());
        restaurantService.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    public Restaurant getWithVotes(int id) {
        log.info("get restaurant {} with votes", id);
        return restaurantService.getWithVotes(id);
    }

    public Restaurant getWithMenus(int id) {
        log.info("get restaurant {} with menus", id);
        return restaurantService.getWithMenus(id);
    }

    public Restaurant getWithVotesAndMenus(int id) {
        log.info("get restaurant {} with votes and menus", id);
        return restaurantService.getWithMenusAndVotes(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return restaurantService.getAll();
    }

    public List<Restaurant> getAllWithVotes() {
        log.info("get all restaurants with votes");
        return restaurantService.getAllWithVotes();
    }

}

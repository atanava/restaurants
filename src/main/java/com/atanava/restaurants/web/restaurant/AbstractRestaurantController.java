package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.*;

//TODO delete this class
public abstract class AbstractRestaurantController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create restaurant {}", restaurant.getName());
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}",restaurant.getId());
        service.update(restaurant);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public Restaurant getWithVotes(int id) {
        log.info("get restaurant {} with votes", id);
        return service.getWithVotes(id);
    }

    public Restaurant getWithMenus(int id) {
        log.info("get restaurant {} with menus", id);
        return service.getWithMenus(id);
    }

    public Restaurant getWithVotesAndMenus(int id) {
        log.info("get restaurant {} with votes and menus", id);
        return service.getWithMenusAndVotes(id);
    }

    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    public List<Restaurant> getAllWithVotes() {
        log.info("get all restaurants with votes");
        return service.getAllWithVotes();
    }

}

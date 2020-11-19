package com.atanava.restaurants.repository.restaurant;

import com.atanava.restaurants.model.Restaurant;

import java.util.List;

public class DataJpaRestaurantRepository implements RestaurantRepository {
    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public Restaurant getWithDishes(int id) {
        return null;
    }

    @Override
    public Restaurant getWithMenus(int id) {
        return null;
    }

    @Override
    public Restaurant getWithVotes(int id) {
        return null;
    }
}

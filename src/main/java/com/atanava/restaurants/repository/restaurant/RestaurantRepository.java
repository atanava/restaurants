package com.atanava.restaurants.repository.restaurant;

import com.atanava.restaurants.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getWithVotes(int id);

    Restaurant getWithMenus(int id);

    Restaurant getWithVotesAndMenus(int id);
}

package com.atanava.restaurants.repository.dish;

import com.atanava.restaurants.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    default boolean delete(int id, int restaurantId) {
        throw new UnsupportedOperationException("You cannot delete an existing dish. Try to deactivate.");
    }

    boolean deactivate(int id, int restaurantId);

    boolean activate(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    List<Dish> getByActive(int restaurantId, boolean active);

}

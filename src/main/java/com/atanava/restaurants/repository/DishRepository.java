package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    default boolean delete(int id, int restaurantId) {

        throw new UnsupportedOperationException();
    }

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

}

package com.atanava.restaurants.repository.dish;

import com.atanava.restaurants.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean activate(int id, int restaurantId, boolean active);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    List<Dish> getByActive(int restaurantId, boolean active);

}

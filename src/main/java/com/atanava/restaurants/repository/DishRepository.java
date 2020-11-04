package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish deactivate(int id, int restaurantId);

    Dish activate(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

}

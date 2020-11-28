package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

//    Menu update(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByRestaurant(int restaurantId);

    List<Menu> getAllByDate(LocalDate date);

}

package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    Menu get(int id, int restaurantId);

    Menu getByRestAndDate(int restaurantId, LocalDate date);

    List<Menu> getAll();

    List<Menu> getAllByRestaurant(int restaurantId);

    List<Menu> getAllByDate(LocalDate date);

}

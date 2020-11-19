package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Menu;

import java.util.Date;
import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    Menu get(int id, int restaurantId);

    List<Menu> getAll();

    List<Menu> getAllByRestaurant(int restaurantId);

    List<Menu> getAllByDate(Date date);

}

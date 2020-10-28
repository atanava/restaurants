package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Menu;

import java.util.List;

public interface MenuRepository {

    Menu save(Menu menu);

    boolean delete(int id);

    Menu get(int id);

    List<Menu> getAll(int restaurantId);

}

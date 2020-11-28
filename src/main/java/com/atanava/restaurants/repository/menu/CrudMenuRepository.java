package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {
//    @Override
//    @Transactional
//    Menu save(Menu menu);
    @Transactional
    @Modifying
    @Query(name = Menu.UPDATE)
    int update(@Param("dishes") List<Dish> dishes, @Param("id") int id, @Param("restaurantId") int restaurantId);
//    int update(@Param("menu") Menu menu, @Param("id") int id, @Param("restaurantId") int restaurantId);

    @Transactional
    @Modifying
    @Query(name = Menu.DELETE)
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query(name = Menu.GET)
    Menu get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query(name = Menu.BY_RESTAURANT)
    List<Menu> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query(name = Menu.BY_DATE)
    List<Menu> getAllByDate(@Param("date") LocalDate date);
}

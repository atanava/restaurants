package com.atanava.restaurants.repository.restaurant;

import com.atanava.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.dishes WHERE r.id = ?1")
    Restaurant getWithDishes(int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.menus WHERE r.id = ?1")
    Restaurant getWithMenus(int id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.votes WHERE r.id = ?1")
    Restaurant getWithVotes(int id);
}

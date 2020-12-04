package com.atanava.restaurants.repository.dish;

import com.atanava.restaurants.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Query(name = Dish.ALL)
    List<Dish> geAll(@Param("restaurantId") int restaurantId);

    @Query(name = Dish.BY_ACTIVE)
    List<Dish> getByActive(@Param("restaurantId") int restaurantId, @Param("active") boolean active);

}

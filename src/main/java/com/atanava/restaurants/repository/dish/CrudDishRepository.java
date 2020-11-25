package com.atanava.restaurants.repository.dish;

import com.atanava.restaurants.model.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Query(name = Dish.BY_RESTAURANT)
    List<Dish> geAll(@Param("restaurantId") int restaurantId);

//    @Transactional
//    @Modifying
//    @Query(name = Dish.DELETE)
////    @Query("DELETE FROM Dish d WHERE d.id=:id")
//    int delete(@Param("id") int id);

}

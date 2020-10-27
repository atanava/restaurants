package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Override
    @Transactional
    Dish save(Dish dish);

    @Transactional
    @Modifying
    @Query(name = Dish.DELETE)
//    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}

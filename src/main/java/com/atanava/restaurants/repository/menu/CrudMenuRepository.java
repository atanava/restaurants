package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query(name = Menu.DELETE)
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(name = Menu.GET)
    Menu get(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(name = Menu.BY_REST_AND_DATE)
    Menu getByRestAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Query(name = Menu.BY_RESTAURANT)
    List<Menu> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query(name = Menu.BY_DATE)
    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> getAllByDate(@Param("date") LocalDate date);
}

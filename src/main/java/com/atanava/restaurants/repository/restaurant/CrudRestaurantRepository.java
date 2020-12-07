package com.atanava.restaurants.repository.restaurant;

import com.atanava.restaurants.model.Restaurant;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query(name = Restaurant.DELETE)
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(name = Restaurant.GET_WITH)
    Restaurant getWithVotes(@Param("id") int id);

    @EntityGraph(attributePaths = {"menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(name = Restaurant.GET_WITH)
    Restaurant getWithMenus(@Param("id") int id);

    @EntityGraph(attributePaths = {"votes", "menus"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(name = Restaurant.GET_WITH)
    Restaurant getWithVotesAndMenus(@Param("id") int id);
}

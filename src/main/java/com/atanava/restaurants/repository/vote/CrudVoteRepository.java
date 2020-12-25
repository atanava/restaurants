package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.model.Vote;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Vote> findById(Integer integer);

    @Override
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> findAll(Sort sort);

    @Transactional
    @Modifying
    @Query(name = Vote.DELETE)
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(name = Vote.DELETE_BY_USER_AND_DATE)
    int deleteByUserAndDate(@Param("userId") int userId, @Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query(name = Vote.UPDATE)
    int update(@Param("id") int id, @Param("userId") int userId, @Param("restaurant") Restaurant restaurant);

    @Query(name = Vote.BY_USER_AND_DATE)
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Vote getByUserAndDate(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query(name = Vote.BY_USER)
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Set<Vote> getAllByUser(@Param("userId") int userId);

    @Query(name = Vote.BY_RESTAURANT)
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Set<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query(name = Vote.BY_DATE)
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Set<Vote> getAllByDate(@Param("date") LocalDate date);

    @Query(name = Vote.BY_REST_AND_DATE)
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Set<Vote> getAllByRestAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Query(name = Vote.BY_USER_AND_REST)
    @EntityGraph(attributePaths = {"user", "restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    Set<Vote> getAllByUserAndRest(@Param("userId") int userId, @Param("restaurantId") int restaurantId);
}

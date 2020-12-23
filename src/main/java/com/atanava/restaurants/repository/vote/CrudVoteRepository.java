package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.model.Vote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @EntityGraph(attributePaths = {"user", "restaurant"})
    Optional<Vote> findById(Integer integer);

    @Transactional
    @Modifying
    @Query(name = Vote.DELETE)
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(name = Vote.UPDATE)
    int update(@Param("id") int id, @Param("userId") int userId, @Param("restaurant") Restaurant restaurant);

    @Query(name = Vote.BY_USER)
    Set<Vote> getAllByUser(@Param("userId") int userId);

    @Query(name = Vote.BY_RESTAURANT)
    Set<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);

    @Query(name = Vote.BY_DATE)
    Set<Vote> getAllByDate(@Param("date") LocalDate date);

    @Query(name = Vote.BY_REST_AND_DATE)
    Set<Vote> getAllByRestAndDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Query(name = Vote.BY_USER_AND_REST)
    Set<Vote> getAllByUserAndRest(@Param("userId") int userId, @Param("restaurantId") int restaurantId);
}

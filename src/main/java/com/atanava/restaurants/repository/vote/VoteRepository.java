package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Set;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int id);

    boolean deleteAllByRestaurant(int restaurantId);

    boolean deleteByUserAndDate(int userId, LocalDate date);

    Vote get(int id, int userId);

    Vote getById(int id);

    Vote getByUserAndDate(int userId, LocalDate date);

    Set<Vote> getAll();

    Set<Vote> getAllByUser(int userId);

    Set<Vote> getAllByRestaurant(int restaurantId);

    Set<Vote> getAllByDate(LocalDate date);

    Set<Vote> getAllByRestAndDate(int restaurantId, LocalDate date);

    Set<Vote> getAllByUserAndRest(int userId, int restaurantId);
}

package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Set;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    boolean delete(int id, int restaurantId);

    Vote get(int id, int restaurantId);

    Set<Vote> getAll();

    Set<Vote> getAllByUser(int userId);

    Set<Vote> getAllByRestaurant(int restaurantId);

    Set<Vote> getAllByDate(LocalDate date);

    Set<Vote> getAllByRestAndDate(int restaurantId, LocalDate date);
}

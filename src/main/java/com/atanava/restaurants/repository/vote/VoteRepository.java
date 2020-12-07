package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Set;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    default boolean delete(int id, int userId) {
        throw new UnsupportedOperationException("You cannot delete an existing vote. Try to re-vote");
    }

    Vote get(int id, int userId);

    Vote getWithUserAndRest(int id);

    Set<Vote> getAll();

    Set<Vote> getAllByUser(int userId);

    Set<Vote> getAllByRestaurant(int restaurantId);

    Set<Vote> getAllByDate(LocalDate date);

    Set<Vote> getAllByRestAndDate(int restaurantId, LocalDate date);
}
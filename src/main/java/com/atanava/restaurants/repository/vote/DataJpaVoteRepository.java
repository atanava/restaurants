package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Vote;

import java.time.LocalDate;
import java.util.Set;

public class DataJpaVoteRepository implements VoteRepository {
    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return false;
    }

    @Override
    public Vote get(int id, int restaurantId) {
        return null;
    }

    @Override
    public Set<Vote> getAll() {
        return null;
    }

    @Override
    public Set<Vote> getAllByUser(int userId) {
        return null;
    }

    @Override
    public Set<Vote> getAllByRestaurant(int restaurantId) {
        return null;
    }

    @Override
    public Set<Vote> getAllByDate(LocalDate date) {
        return null;
    }

    @Override
    public Set<Vote> getAllByRestAndDate(int restaurantId, LocalDate date) {
        return null;
    }
}

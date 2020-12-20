package com.atanava.restaurants.service;

import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.repository.vote.VoteRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Set;

import static com.atanava.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, restaurantId);
    }

    public Vote get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Vote getWithUserAndRest(int id) {
        return checkNotFoundWithId(repository.getWithUserAndRest(id), id);
    }

    public Set<Vote> getAll() {
        return repository.getAll();
    }

    public Set<Vote> getAllByUser(int userId) {
        return repository.getAllByUser(userId);
    }

    public Set<Vote> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId);
    }

    public Set<Vote> getAllByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getAllByDate(date);
    }

    public Set<Vote> getAllByRestAndDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getAllByRestAndDate(restaurantId, date);
    }

    public Set<Vote> getAllByUserAndRest(int userId, int restaurantId) {
        return repository.getAllByUserAndRest(userId, restaurantId);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

}

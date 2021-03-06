package com.atanava.restaurants.service;

import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.repository.vote.VoteRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static com.atanava.restaurants.util.ValidationUtil.*;
import static com.atanava.restaurants.util.VoteUtil.*;

@Service
public class VoteService {

    private static LocalTime EXPIRATION_TIME = LocalTime.of(11, 0, 0, 0);

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    static void setExpirationTime(LocalTime expirationTime) {
        EXPIRATION_TIME = expirationTime;
    }

    private Vote create(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        checkNew(vote);
        return repository.save(vote, userId, restaurantId);
    }

    public VoteTo createOrUpdate(int userId, int restaurantId) {
        Vote vote = repository.getByUserAndDate(userId, LocalDate.now());
        if (vote == null) {
            vote = new Vote();
            vote.setDate(LocalDate.now());
            vote = create(vote, userId, restaurantId);
        } else {
            vote = update(vote, userId, restaurantId);
        }
        return createToFromVote(vote);
    }

    @Transactional
    Vote update(Vote vote, int userId, int restaurantId) throws NotFoundException {
        checkTimeExpired(EXPIRATION_TIME);
        Assert.notNull(vote, "vote must not be null");
        return checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public VoteTo get(int id, int userId) {
        return createToFromVote(repository.get(id, userId));
    }

    public VoteTo getById(int id) {
        return createToFromVote(repository.getById(id));
    }

    public Collection<VoteTo> getAll(Collection<VoteTo> collection) {
        return createTosFromVotes(repository.getAll(), collection);
    }

    public Collection<VoteTo> getAllByUser(int userId, Collection<VoteTo> collection) {
        return createTosFromVotes(repository.getAllByUser(userId), collection);
    }

    public Collection<VoteTo> getAllByRestaurant(int restaurantId, Collection<VoteTo> collection) {
        return createTosFromVotes(repository.getAllByRestaurant(restaurantId), collection);
    }

    public Collection<VoteTo> getAllByDate(LocalDate date, Collection<VoteTo> collection) {
        Assert.notNull(date, "date must not be null");
        return createTosFromVotes(repository.getAllByDate(date), collection);
    }

    public Collection<VoteTo> getAllByRestAndDate(int restaurantId, LocalDate date, Collection<VoteTo> collection) {
        Assert.notNull(date, "date must not be null");
        return createTosFromVotes(repository.getAllByRestAndDate(restaurantId, date), collection);
    }

    public Collection<VoteTo> getAllByUserAndRest(int userId, int restaurantId, Collection<VoteTo> collection) {
        return createTosFromVotes(repository.getAllByUserAndRest(userId, restaurantId), collection);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void deleteByUserByToday(int userId) throws NotFoundException {
        checkTimeExpired(EXPIRATION_TIME);
        if (!repository.deleteByUserAndDate(userId, LocalDate.now())) {
            throw new NotFoundException("today vote of user " + "userId" + " was not found");
        }
    }
}

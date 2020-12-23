package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.repository.restaurant.CrudRestaurantRepository;
import com.atanava.restaurants.repository.user.CrudUserRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private static final Sort SORT_DATE = Sort.by(Sort.Direction.DESC, "date");

    private final CrudVoteRepository crudVoteRepository;
    private final CrudUserRepository crudUserRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudVoteRepository, CrudUserRepository crudUserRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudVoteRepository = crudVoteRepository;
        this.crudUserRepository = crudUserRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        if ( ! vote.isNew()) {
            if (get(vote.getId(), userId) == null || ! vote.getDate().equals(LocalDate.now())) {
                return null;
            } else {
                Restaurant restaurant = crudRestaurantRepository.getOne(restaurantId);
                vote.setRestaurant(restaurant);
                return crudVoteRepository.update(vote.getId(), userId, restaurant) == 0 ? null : vote;
            }
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudVoteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return crudVoteRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElseThrow(() -> new NotFoundException("Vote with id=" + id + " and userId=" + userId + " was not found"));
    }

    @Override
    public Vote getById(int id) {
        return crudVoteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vote with id=" + id + " was not found"));
    }

    @Override
    public Set<Vote> getAll() {
        return new LinkedHashSet<>(crudVoteRepository.findAll(SORT_DATE));
    }

    @Override
    public Set<Vote> getAllByUser(int userId) {
        return new LinkedHashSet<>(crudVoteRepository.getAllByUser(userId));
    }

    @Override
    public Set<Vote> getAllByRestaurant(int restaurantId) {
        return new LinkedHashSet<>(crudVoteRepository.getAllByRestaurant(restaurantId));
    }

    @Override
    public Set<Vote> getAllByDate(LocalDate date) {
        return new LinkedHashSet<>(crudVoteRepository.getAllByDate(date));
    }

    @Override
    public Set<Vote> getAllByRestAndDate(int restaurantId, LocalDate date) {
        return new LinkedHashSet<>(crudVoteRepository.getAllByRestAndDate(restaurantId, date));
    }

    @Override
    public Set<Vote> getAllByUserAndRest(int userId, int restaurantId) {
        return new LinkedHashSet<>(crudVoteRepository.getAllByUserAndRest(userId, restaurantId));
    }
}

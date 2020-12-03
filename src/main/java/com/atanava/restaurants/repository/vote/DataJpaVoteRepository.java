package com.atanava.restaurants.repository.vote;

import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.repository.restaurant.CrudRestaurantRepository;
import com.atanava.restaurants.repository.user.CrudUserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    private static final Sort SORT_DATE_REST = Sort.by(Sort.Direction.DESC, "date", "restaurant");

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
        if ( ! vote.isNew() && get(userId, LocalDate.now()) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public Vote get(int userId, LocalDate date) {
        return crudVoteRepository.get(userId, date);
    }

    @Override
    public Set<Vote> getAll() {
        return new LinkedHashSet<>(crudVoteRepository.findAll(SORT_DATE_REST));
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
}

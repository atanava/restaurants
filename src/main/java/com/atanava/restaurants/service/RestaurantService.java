package com.atanava.restaurants.service;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.restaurant.RestaurantRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public List<Restaurant> getAllWithVotes() {
        return repository.getAllWithVotes();
    }

    public Restaurant getWithMenus(int id) {
        return checkNotFoundWithId(repository.getWithMenus(id), id);
    }

    public Restaurant getWithVotes(int id) {
        return checkNotFoundWithId(repository.getWithVotes(id), id);
    }

    public Restaurant getWithMenusAndVotes(int id) {
        return checkNotFoundWithId(repository.getWithVotesAndMenus(id), id);
    }
}

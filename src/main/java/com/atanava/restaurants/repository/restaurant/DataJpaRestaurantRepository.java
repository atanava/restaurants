package com.atanava.restaurants.repository.restaurant;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository crudRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return crudRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant with id=" + id + " was not found"));
    }

    @Override
    public Restaurant getWithVotes(int id) {
        return crudRepository.getWithVotes(id);
    }

    @Override
    public Restaurant getWithMenus(int id) {
        return crudRepository.getWithMenus(id);
    }

    @Override
    public Restaurant getWithVotesAndMenus(int id) {
        return crudRepository.getWithVotesAndMenus(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }

    @Override
    public List<Restaurant> getAllWithVotes() {
        return crudRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }


}

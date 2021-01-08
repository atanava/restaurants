package com.atanava.restaurants.service;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.repository.dish.DishRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurantId);
    }

    public Dish update(Dish dish, int restaurantId) {
        Assert.isTrue(dish.getRestaurantId() == restaurantId, "dish.restaurantId and restaurantId must be equal");
        return create(dish, restaurantId);
    }

    public boolean deactivate(int id, int restaurantId) {
        if (! repository.deactivate(id, restaurantId)) {
            throw new NotFoundException("Dish with id=" + id + " and restaurantId=" + restaurantId + "  was not found");
        } else return true;
    }

    public boolean activate(int id, int restaurantId) {
        if (! repository.activate(id, restaurantId)) {
            throw new NotFoundException("Dish with id=" + id + " and restaurantId=" + restaurantId + "  was not found");
        } else return true;
    }

    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public List<Dish> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public List<Dish> getByActive(int restaurantId, boolean active) {
        return  repository.getByActive(restaurantId, active);
    }
}

package com.atanava.restaurants.repository.dish;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.repository.restaurant.CrudRestaurantRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudDishRepository crudDishRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudDishRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudDishRepository = crudDishRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew()) {
            throw new UnsupportedOperationException("You cannot modify existing dish, please create new one");
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    @Transactional
    public boolean activate(int id, int restaurantId, boolean active) {
        Dish activated = get(id, restaurantId);
        if (activated == null) {
            return false;
        }
        activated.setActive(active);
        return true;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElseThrow(() -> new NotFoundException("Dish with id=" +
                        id + "  and restaurantId=" + restaurantId + " was not found"));
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.geAll(restaurantId);
    }

    @Override
    public List<Dish> getByActive(int restaurantId, boolean active) {
        return crudDishRepository.getByActive(restaurantId, active);
    }

}

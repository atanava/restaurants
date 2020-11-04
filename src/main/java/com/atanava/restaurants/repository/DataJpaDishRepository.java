package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

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
            if (deactivate(dish.getId(), restaurantId) == null) {
                return null;
            } else {
                Dish newDish = new Dish(null, dish.getRestaurant(), dish.getName(), dish.getPrice());
                return crudDishRepository.save(newDish);
            }
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Transactional
    public Dish deactivate(int id, int restaurantId) {
        Dish deactivated = get(id, restaurantId);
        if (deactivated == null) {
            return null;
        }
        deactivated.setActive(false);
        return crudDishRepository.save(deactivated);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.geAll(restaurantId);
    }

}

package com.atanava.restaurants.repository.dish;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.repository.restaurant.CrudRestaurantRepository;
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
        if ( ! dish.isNew()) {
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

    @Override
    public boolean delete(int id, int restaurantId)  {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional
    public Dish deactivate(int id, int restaurantId) {
        Dish deactivated = get(id, restaurantId);
        if (deactivated == null) {
            return null;
        }
        deactivated.setActive(false);
        return deactivated;
    }

    @Override
    @Transactional
    public Dish activate(int id, int restaurantId) {
        Dish activated = get(id, restaurantId);
        if (activated == null) {
            return null;
        }
        activated.setActive(true);
        return activated;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.geAll(restaurantId);
    }

}

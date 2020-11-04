package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudDishRepository crudRepository;
//    private final

    public DataJpaDishRepository(CrudDishRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        return crudRepository.save(dish);
    }

//    @Override
//    public boolean delete(int id) {
//        return crudRepository.delete(id) != 0;
//    }

    @Override
    public Dish softDelete(int id, int restaurantId) {
        Dish dish = get(id);
        if (dish == null) {
            return null;
        }
        dish.setActive(false);
        return save(dish);
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll(int restaurantId) {
        return crudRepository.geAll(restaurantId);
    }

}

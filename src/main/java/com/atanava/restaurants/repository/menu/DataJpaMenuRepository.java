package com.atanava.restaurants.repository.menu;

import com.atanava.restaurants.model.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DataJpaMenuRepository implements MenuRepository {
    //TODO add sort dy restaurant too
    private static final Sort SORT_DATE = Sort.by(Sort.Direction.DESC, "date");

    private final CrudMenuRepository crudMenuRepository;
//    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository) {
        this.crudMenuRepository = crudMenuRepository;
    }

//    public DataJpaMenuRepository(CrudMenuRepository crudMenuRepository, CrudRestaurantRepository crudRestaurantRepository) {
//        this.crudMenuRepository = crudMenuRepository;
//        this.crudRestaurantRepository = crudRestaurantRepository;
//    }

    @Override
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
//        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudMenuRepository.delete(id, restaurantId) != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public List<Menu> getAll() {
        return crudMenuRepository.findAll(SORT_DATE);
    }

    @Override
    public List<Menu> getAllByRestaurant(int restaurantId) {
        return crudMenuRepository.getAllByRestaurant(restaurantId);
    }

    @Override
    public List<Menu> getAllByDate(Date date) {
        return crudMenuRepository.getAllByDate(date);
    }
}

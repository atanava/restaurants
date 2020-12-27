package com.atanava.restaurants.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.repository.menu.MenuRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenuService {

    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "menus", allEntries = true)
    public Menu create(Menu menu, int restaurantId) {
        Assert.notNull(menu, "menu must not be null");
        return repository.save(menu, restaurantId);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void update(Menu menu, int restaurantId) throws NotFoundException {
        Assert.notNull(menu, "menu must not be null");
        checkNotFoundWithId(repository.save(menu, restaurantId), menu.id());
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    public Menu get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Cacheable("menus")
    public Menu getByRestAndDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getByRestAndDate(restaurantId, date);
    }

    public List<Menu> getAll() {
        return repository.getAll();
    }

    public List<Menu> getAllByRestaurant(int restaurantId) {
        return repository.getAllByRestaurant(restaurantId);
    }

    @Cacheable("menus")
    public List<Menu> getAllByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getAllByDate(date);
    }
}

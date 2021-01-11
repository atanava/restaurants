package com.atanava.restaurants.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.menu.MenuRepository;
import com.atanava.restaurants.repository.restaurant.RestaurantRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.atanava.restaurants.util.ValidationUtil.checkNotFoundWithId;
import static com.atanava.restaurants.util.RestaurantUtil.createToFromRestaurant;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @CacheEvict(value = {"restaurants"}, allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = {"restaurants", "menus"}, allEntries = true)
    @Transactional
    public void update(Restaurant restaurant) throws NotFoundException {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @CacheEvict(value = {"restaurants", "menus"}, allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Cacheable(value = {"restaurants", "menus"})
    public RestaurantTo getToWithMenu(int id, LocalDate date) throws NotFoundException {
        Restaurant restaurant = checkNotFoundWithId(get(id), id);
        Menu menu = menuRepository.getByRestAndDate(id, date);
        return createToFromRestaurant(restaurant, menu);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Cacheable("restaurants")
    public List<RestaurantTo> getAllTos() {
        return getAll()
                .stream()
                .map(r -> createToFromRestaurant(r, null))
                .collect(Collectors.toList());
    }

    public List<Restaurant> getAllWithVotes() {
        return restaurantRepository.getAllWithVotes();
    }

    public Restaurant getWithMenus(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.getWithMenus(id), id);
    }

    public Restaurant getWithVotes(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.getWithVotes(id), id);
    }

    public Restaurant getWithVotesAndMenus(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.getWithVotesAndMenus(id), id);
    }
}

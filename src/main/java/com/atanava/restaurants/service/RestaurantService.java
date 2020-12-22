package com.atanava.restaurants.service;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.menu.MenuRepository;
import com.atanava.restaurants.repository.restaurant.RestaurantRepository;
import com.atanava.restaurants.util.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    public RestaurantTo getTo(int id) throws NotFoundException {
        Restaurant restaurant = checkNotFoundWithId(getWithVotes(id), id);
        return createToFromRestaurant(restaurant, null);
    }

    public RestaurantTo getToWithMenu(int id, LocalDate date) throws NotFoundException {
        Restaurant restaurant = checkNotFoundWithId(getWithVotes(id), id);
        Menu menu = menuRepository.getByRestAndDate(id, date);
        return createToFromRestaurant(restaurant, menu);
    }


    public void update(Restaurant restaurant) throws NotFoundException {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public List<Restaurant> getAllWithVotes() {
        return restaurantRepository.getAllWithVotes();
    }

    public Restaurant getWithMenus(int id) {
        return checkNotFoundWithId(restaurantRepository.getWithMenus(id), id);
    }

    public Restaurant getWithVotes(int id) {
        return checkNotFoundWithId(restaurantRepository.getWithVotes(id), id);
    }

    public Restaurant getWithMenusAndVotes(int id) throws NotFoundException {
        return checkNotFoundWithId(restaurantRepository.getWithVotesAndMenus(id), id);
    }
}

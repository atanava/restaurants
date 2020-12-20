package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.service.RestaurantService;
import com.atanava.restaurants.util.DtoConverter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.atanava.restaurants.util.DtoConverter.createToFromRestaurant;

@RestController
@RequestMapping(value = ProfileRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController {

    static final String REST_URL = "/rest/profile/restaurants";

    private final RestaurantService restaurantService;

    public ProfileRestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        return restaurantService.getToWithMenu(id, LocalDate.of(2020, 11, 19));
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        return restaurantService.getAllWithVotes()
                .stream()
                .map(r -> createToFromRestaurant(r, null))
                .collect(Collectors.toList());
    }
}
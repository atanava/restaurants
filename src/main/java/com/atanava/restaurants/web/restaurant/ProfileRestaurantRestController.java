package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.service.RestaurantService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/profile/restaurants";

    private final RestaurantService restaurantService;

    public ProfileRestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public RestaurantTo getTo(@PathVariable int id) {
        return restaurantService.getTo(id, LocalDate.of(2020, 11, 19));
    }

    @Override
    @GetMapping("/all-with-votes")
    public List<Restaurant> getAllWithVotes() {
        return super.getAllWithVotes();
    }
}

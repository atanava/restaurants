package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/profile/restaurants";

    private final RestaurantService restaurantService;

    public ProfileRestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public RestaurantTo getTo(@PathVariable int id) {
        log.info("get restaurant {} with today menu", id);
        return restaurantService.getTo(id, LocalDate.now());
    }

    @GetMapping
    public List<RestaurantTo> getAllTos() {
        log.info("get all restaurant TOs");
        return restaurantService.getAllTos();
    }
}

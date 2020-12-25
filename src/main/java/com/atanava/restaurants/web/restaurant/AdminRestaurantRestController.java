package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.*;

@RestController
@RequestMapping(value = AdminRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/restaurants";

    private final RestaurantService service;

    public AdminRestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create restaurant {}", restaurant.getName());

        Restaurant created = service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        assureIdConsistent(restaurant, id);
        log.info("update restaurant {}", id);
        service.update(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    @GetMapping("/with-votes/{id}")
    public Restaurant getWithVotes(@PathVariable int id) {
        log.info("get restaurant {} with votes", id);
        return service.getWithVotes(id);
    }

    @GetMapping("/with-menus/{id}")
    public Restaurant getWithMenus(@PathVariable int id) {
        log.info("get restaurant {} with menus", id);
        return service.getWithMenus(id);
    }

    @GetMapping("/with-votes-and-menus/{id}")
    public Restaurant getWithVotesAndMenus(@PathVariable int id) {
        log.info("get restaurant {} with votes and menus", id);
        return service.getWithMenusAndVotes(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping("/all-with-votes")
    public List<Restaurant> getAllWithVotes() {
        log.info("get all restaurants with votes");
        return service.getAllWithVotes();
    }
}

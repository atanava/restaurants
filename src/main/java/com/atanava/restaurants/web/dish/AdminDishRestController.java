package com.atanava.restaurants.web.dish;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.service.DishService;
import com.atanava.restaurants.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.atanava.restaurants.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/dishes";

    private final DishService service;

    public AdminDishRestController(DishService service) {
        this.service = service;
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create dish {} for restaurant {}", dish.getName(), restaurantId);

        Dish created = service.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/deactivate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(dish, id);
        log.info("deactivate dish {} from restaurant {}", id, restaurantId);
        service.deactivate(id, restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/activate/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(dish, id);
        log.info("activate dish {} from restaurant {}", id, restaurantId);
        service.activate(id, restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/{id}")
    public Dish get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get dish {} from restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @GetMapping(value = "/{restaurantId}")
    public List<Dish> getAll(@PathVariable int restaurantId) {
        log.info("get all dishes from restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/active")
    public List<Dish> getAllActive(@PathVariable int restaurantId) {
        log.info("get all active dishes from restaurant {}", restaurantId);
        return service.getByActive(restaurantId, true);
    }

    @GetMapping(value = "/{restaurantId}/inactive")
    public List<Dish> getAllInActive(@PathVariable int restaurantId) {
        log.info("get all inactive dishes from restaurant {}", restaurantId);
        return service.getByActive(restaurantId, false);
    }

}

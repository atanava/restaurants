package com.atanava.restaurants.web.dish;

import com.atanava.restaurants.View;
import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.service.DishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDishRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/dishes";

    private final DishService service;

    public AdminDishRestController(DishService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Dish> create(@Validated(View.Web.class) @RequestBody Dish dish, @RequestParam int restaurantId) {
        log.info("create dish {} for restaurant {}", dish.getName(), restaurantId);

        Dish created = service.create(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping(value = "/deactivate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivate(@RequestParam int restaurantId, @RequestParam int id) {
        log.info("deactivate dish {} from restaurant {}", id, restaurantId);
        service.deactivate(id, restaurantId);
    }

    @PatchMapping(value = "/activate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activate(@RequestParam int restaurantId, @RequestParam int id) {
        log.info("activate dish {} from restaurant {}", id, restaurantId);
        service.activate(id, restaurantId);
    }

    @GetMapping
    public Dish get(@RequestParam int restaurantId, @RequestParam int id) {
        log.info("get dish {} from restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @GetMapping("/all")
    public List<Dish> getAll(@RequestParam int restaurantId) {
        log.info("get all dishes from restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @GetMapping("/active")
    public List<Dish> getAllActive(@RequestParam int restaurantId) {
        log.info("get all active dishes from restaurant {}", restaurantId);
        return service.getByActive(restaurantId, true);
    }

    @GetMapping("/inactive")
    public List<Dish> getAllInActive(@RequestParam int restaurantId) {
        log.info("get all inactive dishes from restaurant {}", restaurantId);
        return service.getByActive(restaurantId, false);
    }

}

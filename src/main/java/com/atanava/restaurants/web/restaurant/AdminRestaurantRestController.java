package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.View;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.service.RestaurantService;
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
import java.util.Optional;

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
    public ResponseEntity<Restaurant> create(@Validated(View.Web.class) @RequestBody Restaurant restaurant) {
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
    public void update(@Validated(View.Web.class) @RequestBody Restaurant restaurant, @PathVariable int id) {
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
    public Restaurant get(@PathVariable int id,
                          @RequestParam(required = false) Optional<Boolean> votes,
                          @RequestParam(required = false) Optional<Boolean> menus) {

        boolean withVotes = votes.orElse(false);
        boolean withMenus = menus.orElse(false);
        log.info("get restaurant {} with votes={} and menus={}", id, withVotes, withMenus);

        if (withVotes && withMenus) {
            return service.getWithVotesAndMenus(id);
        } else if (withVotes) {
            return service.getWithVotes(id);
        } else if (withMenus) {
            return service.getWithMenus(id);
        }
        return service.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll(@RequestParam(required = false) Optional<Boolean> votes) {
        boolean withVotes = votes.orElse(false);
        log.info("get all restaurants with votes={}", withVotes);

        return withVotes ? service.getAllWithVotes() : service.getAll();
    }
}

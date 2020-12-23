package com.atanava.restaurants.web.vote;

import com.atanava.restaurants.model.Vote;
import com.atanava.restaurants.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping(value = AdminVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/admin/votes";

    private final VoteService service;

    public AdminVoteRestController(VoteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get vote {}", id);
        return service.getById(id);
    }

    @GetMapping
    public Set<Vote> getAll() {
        log.info("get all votes");
        return service.getAll();
    }

    @GetMapping("/by-user/{userId}")
    public Set<Vote> getAllByUser(@PathVariable int userId) {
        log.info("get all votes by user {}", userId);
        return service.getAllByUser(userId);
    }

    @GetMapping("/by-restaurant/{restaurantId}")
    public Set<Vote> getAllByRestaurant(@PathVariable int restaurantId) {
        log.info("get all votes by restaurant {}", restaurantId);
        return  service.getAllByRestaurant(restaurantId);
    }

    @GetMapping("/by-date")
    public Set<Vote> getAllByDate(@RequestBody LocalDate date) {
        log.info("get all votes by date {}", date);
        return service.getAllByDate(date);
    }

    @GetMapping("/by-restaurant-and-date/{restaurantId}")
    public Set<Vote> getAllByRestAndDate(@PathVariable int restaurantId, @RequestBody LocalDate date) {
        log.info("get all votes by restaurant {} and date {}", restaurantId, date);
        return service.getAllByRestAndDate(restaurantId, date);
    }

    @GetMapping("/by-user-and-restaurant/{userId}/{restaurantId}")
    public Set<Vote> getAllByUserAndRest(@PathVariable int userId, @PathVariable int restaurantId) {
        log.info("get all votes by user {} and restaurant {}", userId, restaurantId);
        return service.getAllByUserAndRest(userId, restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("get vote {}", id);
        service.delete(id);
    }
}

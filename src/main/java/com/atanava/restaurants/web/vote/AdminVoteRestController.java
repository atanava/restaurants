package com.atanava.restaurants.web.vote;

import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public VoteTo get(@PathVariable int id) {
        log.info("get vote {}", id);
        return service.getById(id);
    }

    @GetMapping
    public List<VoteTo> getAll() {
        log.info("get all votes");
        return (List<VoteTo>) service.getAll(new ArrayList<>());
    }

    @GetMapping("/by-user")
    public List<VoteTo> getAllByUser(@RequestParam int userId) {
        log.info("get all votes by user {}", userId);
        return (List<VoteTo>) service.getAllByUser(userId, new ArrayList<>());
    }

    @GetMapping("/by-restaurant")
    public List<VoteTo> getAllByRestaurant(@RequestParam int restaurantId) {
        log.info("get all votes by restaurant {}", restaurantId);
        return (List<VoteTo>) service.getAllByRestaurant(restaurantId, new ArrayList<>());
    }

    @GetMapping("/by-date")
    public List<VoteTo> getAllByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("get all votes by date {}", date);
        return (List<VoteTo>) service.getAllByDate(date, new ArrayList<>());
    }

    @GetMapping("/by-restaurant-and-date")
    public List<VoteTo> getAllByRestAndDate(@RequestParam int restaurantId,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("get all votes by restaurant {} and date {}", restaurantId, date);
        return (List<VoteTo>) service.getAllByRestAndDate(restaurantId, date, new ArrayList<>());
    }

    @GetMapping("/by-user-and-restaurant")
    public List<VoteTo> getAllByUserAndRest(@RequestParam int userId, @RequestParam int restaurantId) {
        log.info("get all votes by user {} and restaurant {}", userId, restaurantId);
        return (List<VoteTo>) service.getAllByUserAndRest(userId, restaurantId, new ArrayList<>());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("get vote {}", id);
        service.delete(id);
    }
}

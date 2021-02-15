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
import java.util.*;

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
    public List<VoteTo> getAll(@RequestParam(required = false) Integer userId,
                               @RequestParam(required = false) Integer restaurantId,
                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        log.info("get all votes by user={} and restaurant={} and date={}", userId, restaurantId, date);

        List<VoteTo> voteTos = new ArrayList<>();
        return    (userId != null && restaurantId != null && date == null) ? (List<VoteTo>) service.getAllByUserAndRest(userId, restaurantId, voteTos)
                : (userId == null && restaurantId != null && date != null) ? (List<VoteTo>) service.getAllByRestAndDate(restaurantId, date, voteTos)
                : (userId == null && restaurantId != null && date == null) ? (List<VoteTo>) service.getAllByRestaurant(restaurantId, voteTos)
                : (userId != null && restaurantId == null && date == null) ? (List<VoteTo>) service.getAllByUser(userId, voteTos)
                : (userId == null && restaurantId == null && date != null) ? (List<VoteTo>) service.getAllByDate(date, voteTos)
                : (userId == null && restaurantId == null && date == null) ? (List<VoteTo>) service.getAll(voteTos)
                : null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("get vote {}", id);
        service.delete(id);
    }
}

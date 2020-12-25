package com.atanava.restaurants.web.vote;

import com.atanava.restaurants.AuthorizedUser;
import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = ProfileVoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileVoteRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/profile/votes";

    private final VoteService service;

    public ProfileVoteRestController(VoteService service) {
        this.service = service;
    }

    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOrUpdate(@AuthenticationPrincipal AuthorizedUser authUser, @PathVariable("restaurantId") int restaurantId) {
        log.info("create vote for user {} and restaurant {}", authUser.getId(), restaurantId);
        service.createOrUpdate(authUser.getId(), restaurantId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("delete vote {} from user {} ", id, authUser.getId());
        service.deleteByUserByToday(id, authUser.getId());
    }

    @GetMapping("/{id}")
    public VoteTo get(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get voteTo {} of user {} ", id, authUser.getId());
        return service.get(id, authUser.getId());
    }

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get all voteTos of user {} ", authUser.getId());
        return (List<VoteTo>) service.getAllByUser(authUser.getId(), new ArrayList<>());
    }
}

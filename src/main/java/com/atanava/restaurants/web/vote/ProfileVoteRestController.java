package com.atanava.restaurants.web.vote;

import com.atanava.restaurants.AuthorizedUser;
import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteTo> create(@AuthenticationPrincipal AuthorizedUser authUser, @RequestParam("restaurantId") int restaurantId) {
        log.info("create vote for user {} and restaurant {}", authUser.getId(), restaurantId);
        VoteTo created = service.createOrUpdate(authUser.getId(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthorizedUser authUser, @RequestParam("restaurantId") int restaurantId) {
        log.info("update vote for user {} and restaurant {}", authUser.getId(), restaurantId);
        service.createOrUpdate(authUser.getId(), restaurantId);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("delete today vote from user {} ", authUser.getId());
        service.deleteByUserByToday(authUser.getId());
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

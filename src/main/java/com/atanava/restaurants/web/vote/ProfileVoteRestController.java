package com.atanava.restaurants.web.vote;

import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

public class ProfileVoteRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/profile/votes";

    private final VoteService service;

    public ProfileVoteRestController(VoteService service) {
        this.service = service;
    }

}

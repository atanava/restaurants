package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {
    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

    static final String REST_URL = "/rest/admin";

    public Restaurant get() {
        return null;
    }
}

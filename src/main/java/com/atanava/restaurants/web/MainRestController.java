package com.atanava.restaurants.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = MainRestController.REST_URL)
public class MainRestController {

    private static final Logger log = LoggerFactory.getLogger(MainRestController.class);

    static final String REST_URL = "/rest/login";
}

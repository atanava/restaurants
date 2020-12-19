package com.atanava.restaurants.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = MainRestController.REST_URL)
public class MainRestController {

    static final String REST_URL = "/rest";
}

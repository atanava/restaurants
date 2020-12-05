package com.atanava.restaurants.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController {

    static final String REST_URL = "/rest/profile";
}

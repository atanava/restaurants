package com.atanava.restaurants.web.user;

import com.atanava.restaurants.web.AbstractControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.atanava.restaurants.testdata.UserTestData.*;
import static com.atanava.restaurants.TestUtil.*;

//@Disabled("the testcase is under development")
class AdminRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestController.REST_URL + '/';

    @Test
    void getAll() {
    }

    @Test
    void get() {
    }

    @Test
    void createWithLocation() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void getByEmail() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by?email=" + admin.getEmail())
                .with(userAuth(admin)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(admin));
    }

    @Test
    void enable() {
    }
}
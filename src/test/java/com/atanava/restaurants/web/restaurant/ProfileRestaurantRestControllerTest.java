package com.atanava.restaurants.web.restaurant;

import com.atanava.restaurants.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.atanava.restaurants.TestUtil.userHttpBasic;
import static com.atanava.restaurants.util.exception.ErrorType.DATA_NOT_FOUND;
import static com.atanava.restaurants.testdata.RestaurantTestData.*;
import static com.atanava.restaurants.testdata.UserTestData.user1;
import static com.atanava.restaurants.testdata.DbSequence.RESTAURANT_1;
import static com.atanava.restaurants.testdata.DbSequence.NOT_FOUND;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestaurantRestController.REST_URL + '/';

    @Test
    void getTo() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1.id)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_TO_MATCHER.contentJson(getRestTo()));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NOT_FOUND.id)
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(DATA_NOT_FOUND));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAllTos() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(REST_TO_WITHOUT_MENUS_MATCHER.contentJson(getAllExpRestTos()));
    }
}
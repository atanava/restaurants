package com.atanava.restaurants.web.dish;

import com.atanava.restaurants.model.Dish;
import com.atanava.restaurants.service.DishService;
import com.atanava.restaurants.web.AbstractControllerTest;
import com.atanava.restaurants.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.DishTestData.*;
import static com.atanava.restaurants.testdata.UserTestData.admin;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.TestUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.atanava.restaurants.util.exception.ErrorType.VALIDATION_ERROR;

class AdminDishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminDishRestController.REST_URL + '/';

    @Autowired
    private DishService dishService;

    @Test
    void create() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_1.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
                .with(userHttpBasic(admin)));

        Dish created = readFromJson(action, Dish.class);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishService.get(newId, RESTAURANT_1.id), newDish);
    }

    @Test
    void createInvalid() throws Exception {
        Dish invalid = new Dish(null, null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_1.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void createWithNegativePrice() throws Exception {
        Dish invalid = new Dish(null, "Juice", null, -300);
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_1.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void deactivate() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + "deactivate?restaurantId=" + RESTAURANT_1.id + "&id=" + DISH_1.id)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(dishService.get(DISH_1.id, RESTAURANT_1.id).isActive());
    }

    @Test
    void deactivatedNotFound() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + "deactivate?restaurantId=" + RESTAURANT_1.id + "&id=" + NOT_FOUND.id)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void activate() throws Exception {
        dishService.deactivate(DISH_1.id, RESTAURANT_1.id);
        perform(MockMvcRequestBuilders.patch(REST_URL + "activate?restaurantId=" + RESTAURANT_1.id + "&id=" + DISH_1.id)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertTrue(dishService.get(DISH_1.id, RESTAURANT_1.id).isActive());
    }

    @Test
    void activatedNotFound() throws Exception {
        perform(MockMvcRequestBuilders.patch(REST_URL + "activate?restaurantId=" + RESTAURANT_1.id + "&id=" + NOT_FOUND.id)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&id=" + DISH_1.id)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(getAllFromRest1().get(0)));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&id=" + NOT_FOUND.id)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "all?restaurantId=" + RESTAURANT_1.id)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(getAllFromRest1Sorted()));
    }

    @Test
    void getAllActive() throws Exception {
        dishService.deactivate(DISH_4.id, RESTAURANT_1.id);
        List<Dish> allActive = getAllFromRest1Sorted();
        allActive.remove(0);
        perform(MockMvcRequestBuilders.get(REST_URL + "all?restaurantId=" + RESTAURANT_1.id + "&active=true")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(allActive));
    }

}
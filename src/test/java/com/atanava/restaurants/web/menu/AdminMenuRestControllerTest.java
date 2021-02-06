package com.atanava.restaurants.web.menu;

import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.service.MenuService;
import com.atanava.restaurants.util.exception.NotFoundException;
import com.atanava.restaurants.web.AbstractControllerTest;
import com.atanava.restaurants.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.atanava.restaurants.TestUtil.userHttpBasic;
import static com.atanava.restaurants.testdata.UserTestData.admin;
import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.MenuTestData.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.TestUtil.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.atanava.restaurants.util.exception.ErrorType.VALIDATION_ERROR;
import static com.atanava.restaurants.web.ExceptionInfoHandler.EXCEPTION_DUPLICATE_REST_ID_DATE;

class AdminMenuRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMenuRestController.REST_URL + "/";

    @Autowired
    MenuService menuService;

    @Test
    void create() throws Exception {
        Menu newMenu = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_2.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenu))
                .with(userHttpBasic(admin)));

        Menu created = readFromJson(action, Menu.class);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuService.get(newId, RESTAURANT_2.id), newMenu);
    }

    @Test
    void createInvalid() throws Exception {
        Menu invalid = new Menu(null, null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_2.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void PastDateCreate() throws Exception {
        Menu withPastDate = getNew();
        withPastDate.setDate(today.minusDays(1));
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_2.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(withPastDate))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void duplicateDateCreate() throws Exception {
        menuService.create(getNew(), RESTAURANT_2.id);
        Menu duplicate = getNew();
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurantId=" + RESTAURANT_2.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(duplicate))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andExpect(detailMessage(EXCEPTION_DUPLICATE_REST_ID_DATE));
    }

    @Test
    void update() throws Exception {
        Menu updated = getUpdated();
        updated.getDishes().remove(4);
        perform(MockMvcRequestBuilders.put(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + MENU_3.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());

        MENU_MATCHER.assertMatch(menuService.get(MENU_3.id, RESTAURANT_1.id), updated);
    }

    @Test
    void updateInvalid() throws Exception {
        Menu invalid = new Menu(MENU_3.id, null, Collections.emptyList(), null);
        perform(MockMvcRequestBuilders.put(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + MENU_3.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void PastDateUpdate() throws Exception {
        Menu withPastDate= getUpdated();
        withPastDate.getDishes().remove(4);
        withPastDate.setDate(today.minusDays(1));
        perform(MockMvcRequestBuilders.put(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + MENU_3.id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(withPastDate))
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + MENU_3.id)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> menuService.get(MENU_3.id, RESTAURANT_1.id));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + NOT_FOUND.id)
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + MENU_1.id)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menuOfTroika1));
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + MENU_3.id))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&menuId=" + NOT_FOUND.id)
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getByRestaurantAndDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=" + RESTAURANT_1.id + "&date=" + today)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menuOfTroika2));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "all")
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(getAllExpected()));
    }

    @Test
    void getAllByRestaurant() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "all" + "?restaurantId=" + RESTAURANT_1.id)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(getAllExpByRest1()));
    }

    @Test
    void getAllByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "all" + "?date=" + date1)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(getAllExpByDate()));
    }
}


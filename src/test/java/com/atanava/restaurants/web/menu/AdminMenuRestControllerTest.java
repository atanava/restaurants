package com.atanava.restaurants.web.menu;

import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.service.MenuService;
import com.atanava.restaurants.web.AbstractControllerTest;
import com.atanava.restaurants.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.atanava.restaurants.TestUtil.userHttpBasic;
import static com.atanava.restaurants.testdata.UserTestData.admin;
import static org.junit.jupiter.api.Assertions.*;
import static com.atanava.restaurants.testdata.MenuTestData.*;
import static com.atanava.restaurants.testdata.DbSequence.*;
import static com.atanava.restaurants.TestUtil.*;

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
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }
}
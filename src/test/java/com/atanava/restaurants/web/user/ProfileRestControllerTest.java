package com.atanava.restaurants.web.user;

import com.atanava.restaurants.dto.UserTo;
import com.atanava.restaurants.model.User;
import com.atanava.restaurants.service.UserService;
import com.atanava.restaurants.util.UserUtil;
import com.atanava.restaurants.web.AbstractControllerTest;
import com.atanava.restaurants.web.json.JsonUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.atanava.restaurants.TestUtil.*;
import static com.atanava.restaurants.testdata.UserTestData.*;
import static com.atanava.restaurants.testdata.DbSequence.USER_1;
import static com.atanava.restaurants.web.user.ProfileRestController.REST_URL;

//@Disabled("the testcase is under development")
public class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void register() throws Exception {
        UserTo newTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");
        User newUser = UserUtil.createNewFromTo(newTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = readFromJson(action, User.class);
        int newId = created.getId();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(userService.get(newId), newUser);

    }

    @Test
    public void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user1));
    }

//    @Test
//    public void getUnAuth() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isUnauthorized());
//    }
//
//
//    @Test
//    public void delete() throws Exception {
//        perform(MockMvcRequestBuilders.delete(REST_URL)
//                .with(userHttpBasic(user1)))
//                .andExpect(status().isNoContent());
//        USER_MATCHER.assertMatch(userService.getAll(), admin);
//
//    }
//
//    @Test
//    public void update() throws Exception {
//        UserTo updatedTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword");
//        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
//                .with(userHttpBasic(user1))
//                .content(JsonUtil.writeValue(updatedTo)))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        USER_MATCHER.assertMatch(userService.get(USER_1.id), UserUtil.updateFromTo(new User(user1), updatedTo));
//
//    }
}
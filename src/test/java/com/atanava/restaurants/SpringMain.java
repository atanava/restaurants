package com.atanava.restaurants;

import com.atanava.restaurants.model.Menu;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.repository.menu.DataJpaMenuRepository;
import com.atanava.restaurants.repository.menu.MenuRepository;
import com.atanava.restaurants.testdata.DbSequence;
import com.atanava.restaurants.testdata.RestaurantTestData;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

public class SpringMain {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml"
                , "spring/spring-db.xml")) {
        }
    }
}

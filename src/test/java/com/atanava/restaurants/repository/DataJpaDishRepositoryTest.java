package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static com.atanava.restaurants.DbSequence.*;
import static com.atanava.restaurants.DishTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DataJpaDishRepositoryTest {

    @Autowired
    DishRepository repository;

    @Test
    public void save() {
        Dish saved = repository.save(getNew());
        int newId = saved.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        assertEquals(repository.get(newId), newDish);
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
    }
}
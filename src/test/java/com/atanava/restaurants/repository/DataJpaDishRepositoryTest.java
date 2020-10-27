package com.atanava.restaurants.repository;

import com.atanava.restaurants.model.Dish;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
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
    public void duplicateSaved() throws Exception {
        assertThrows(DataAccessException.class, () -> repository.save(new Dish(null ,"Salad")));
    }

    @Test
    public void delete() {
        repository.delete(DISH1_ID.value);
        assertNull(repository.get(DISH1_ID.value));
    }

    @Test
    public void get() {
        Dish dish = repository.get(DISH1_ID.value);
        assertThat(dish).isEqualTo(expectedDish1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertNull(repository.get(NOT_FOUND.value));
    }

    @Test
    public void update() throws Exception {
        Dish updated = getUpdated();
        repository.save(updated);
        assertThat(repository.get(DISH1_ID.value)).isEqualTo(getUpdated());
    }

    @Test
    public void getAll() {
        List<Dish> all = repository.getAll();
        assertThat(all).isEqualTo(getSorted());
    }
}
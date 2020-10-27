package com.atanava.restaurants.model;

import javax.persistence.*;

@NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id")
@Entity
@Table(name =  "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_dish_idx")})
public class Dish extends AbstractNamedEntity{

    public static final String DELETE = "Dish delete";

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName());
    }

    public Dish(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

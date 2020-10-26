package com.atanava.restaurants.model;

import javax.persistence.*;

@Entity
@Table(name =  "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_dish_idx")})
public class Dish extends AbstractNamedEntity{


    public Dish() {
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

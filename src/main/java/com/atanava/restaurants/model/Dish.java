package com.atanava.restaurants.model;

import javax.persistence.*;

@Entity
@Table(name =  "dishes")
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

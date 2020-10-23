package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name =  "dishes")
public class Dish extends AbstractNamedEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", columnDefinition = "date default current_date", nullable = false)
    @NotNull
    Date date = new Date();

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    public Dish() {
    }

    public Dish(Integer id, String name, Restaurant restaurant, Date date, Integer price) {
        super(id, name);
        this.restaurant = restaurant;
        this.date = date;
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer priceInCents) {
        this.price = priceInCents;
    }
    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", restaurant=" + restaurant.name +
                ", date=" + date +
                '}';
    }

}

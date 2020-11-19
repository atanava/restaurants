package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.BY_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.date"),
        @NamedQuery(name = Menu.BY_DATE, query = "SELECT m FROM Menu m WHERE m.date=:date"),
})

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String BY_RESTAURANT = "Menu.getAllByRestaurant";
    public static final String BY_DATE = "Menu.getAllByDate";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dishes_menus",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private Set<Dish> dishes;

    @Column(name = "date", columnDefinition = "date default current_date", nullable = false)
    @NotNull
    private Date date = new Date();

    public Menu() {
    }

    public Menu(Integer id, Restaurant restaurant) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = Collections.emptySet();
    }

    public Menu(Integer id, Restaurant restaurant, Set<Dish> dishes, Date date) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

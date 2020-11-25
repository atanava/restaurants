package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.BY_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC"),
        @NamedQuery(name = Menu.BY_DATE, query = "SELECT m FROM Menu m WHERE m.date=:date"),
})

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"},
        name = "restaurant_id_date_idx")})
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
    private List<Dish> dishes;

    @Column(name = "date", columnDefinition = "date default current_date", nullable = false)
    @NotNull
    private LocalDate date;

    public Menu() {
    }

    public Menu(Restaurant restaurant, List<Dish> dishes) {
        this(restaurant, dishes, null);
    }

    public Menu(Restaurant restaurant, List<Dish> dishes, LocalDate date) {
        this(null, restaurant, dishes, date);
    }

    public Menu(Menu m) {
        this(m.getId(), m.getRestaurant(), m.getDishes(), m.getDate());
    }

    public Menu(Integer id, Restaurant restaurant, List<Dish> dishes, LocalDate date) {
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

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}

package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.BY_RESTAURANT, query = "SELECT d FROM Dish d "
                + " WHERE d.restaurant.id=:restaurantId ORDER BY d.name"),
})

@Entity
@Table(name =  "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id","name"}
                                                , name = "unique_restaurant_id_dish_name_idx")})
public class Dish extends AbstractNamedEntity{

    public static final String DELETE = "Dish.delete";
    public static final String BY_RESTAURANT = "Dish.getByRestaurant";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Column(name = "is_in_menu", nullable = false, columnDefinition = "bool default false")
    private boolean isInMenu = false;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    

    public Dish() {
    }

    public Dish(Dish d) {
        this(d.getId(), d.getRestaurant(), d.getName(), d.isEnabled(), d.isInMenu(), d.getPrice());
    }

    public Dish(Integer id, Restaurant restaurant, String name, boolean enabled, boolean isInMenu, Integer price) {
        super(id, name);
        this.restaurant = restaurant;
        this.enabled = enabled;
        this.isInMenu = isInMenu;
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isInMenu() {
        return isInMenu;
    }

    public void setInMenu(boolean inMenu) {
        isInMenu = inMenu;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

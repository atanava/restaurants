package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

@NamedQueries({
//        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
        @NamedQuery(name = Dish.BY_RESTAURANT, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.name"),
})

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name"},
        name = "unique_restaurant_id_dish_name_idx")})
public class  Dish extends AbstractNamedEntity {

//    public static final String DELETE = "Dish.delete";
    public static final String BY_RESTAURANT = "Dish.getAllByRestaurant";

    //TODO try to replace with restaurantId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    @Column(name = "active", nullable = false, columnDefinition = "bool default true")
    private boolean active = true;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
    Set<Menu> menus;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getRestaurant(), dish.getName(), dish.getPrice());
    }

    public Dish(Integer id, Restaurant restaurant, String name, Integer price) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.menus = Collections.emptySet();
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean enabled) {
        this.active = enabled;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}

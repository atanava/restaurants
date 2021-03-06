package com.atanava.restaurants.model;

import com.atanava.restaurants.View;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collections;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.name"),
        @NamedQuery(name = Dish.BY_ACTIVE, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.active=:active ORDER BY d.name"),
})

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name", "price"},
        name = "unique_restaurant_id_dish_name_price_idx")})
public class  Dish extends AbstractNamedEntity {

    public static final String ALL = "Dish.getAllByRestaurant";
    public static final String BY_ACTIVE = "Dish.getByActive";

    @ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    //    https://stackoverflow.com/questions/6311776/hibernate-foreign-keys-instead-of-entities
    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private Integer restaurantId;

    @Column(name = "price", nullable = false)
    @NotNull
    @PositiveOrZero
    private Integer price;

    @Column(name = "active", nullable = false, columnDefinition = "bool default true")
    private boolean active = true;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
    Set<Menu> menus;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getRestaurant(), dish.getPrice());
    }

    public Dish(Integer id, String name, Restaurant restaurant, Integer price) {
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}

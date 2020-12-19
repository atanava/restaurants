package com.atanava.restaurants.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.beans.ConstructorProperties;
import java.util.Objects;

public class DishTo extends AbstractNamedTo {

    @NotNull
    private final RestaurantTo restaurantTo;

    @NotNull
    @PositiveOrZero
    private final Integer price;

    @ConstructorProperties({"id", "name", "restaurantTo", "price"})
    public DishTo(Integer id, String name, RestaurantTo restaurantTo, Integer price) {
        super(id, name);
        this.price = price;
        this.restaurantTo = restaurantTo;
    }

    public RestaurantTo getRestaurantTo() {
        return restaurantTo;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishTo dishTo = (DishTo) o;
        return Objects.equals(id, dishTo.id) &&
                Objects.equals(name, dishTo.name) &&
                Objects.equals(restaurantTo.id, dishTo.restaurantTo.id) &&
                Objects.equals(price, dishTo.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, restaurantTo.id, price);
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name=" + name +
                ", restaurantTo=" + restaurantTo.name +
                ", price=" + price +
                '}';
    }
}

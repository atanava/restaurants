package com.atanava.restaurants.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.beans.ConstructorProperties;
import java.util.Objects;

public class DishTo extends AbstractNamedTo {

    @NotNull
    @Positive
    private final Integer restaurantId;

    @NotNull
    @PositiveOrZero
    private final Integer price;

    @ConstructorProperties({"id", "name", "restaurantId", "price"})
    public DishTo(Integer id, String name, Integer restaurantId, Integer price) {
        super(id, name);
        this.price = price;
        this.restaurantId = restaurantId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
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
                Objects.equals(restaurantId, dishTo.restaurantId) &&
                Objects.equals(price, dishTo.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, restaurantId, price);
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name=" + name +
                ", restaurantId=" + restaurantId +
                ", price=" + price +
                '}';
    }
}

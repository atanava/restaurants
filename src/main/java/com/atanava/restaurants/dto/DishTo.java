package com.atanava.restaurants.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.beans.ConstructorProperties;

public class DishTo extends NamedTo {

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
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name=" + name +
                ", restaurantId=" + restaurantId +
                ", price=" + price +
                '}';
    }
}

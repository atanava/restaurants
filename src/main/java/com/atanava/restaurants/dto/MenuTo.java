package com.atanava.restaurants.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MenuTo extends AbstractBaseTo {

    @NotNull
    @JsonBackReference
    private final RestaurantTo restaurantTo;

    private final List<DishTo> dishTos;

    @FutureOrPresent
    private final LocalDate date;

    @ConstructorProperties({"id", "restaurantTo", "dishTos", "date"})
    public MenuTo(Integer id, RestaurantTo restaurantTo, List<DishTo> dishTos, LocalDate date) {
        super(id);
        this.restaurantTo = restaurantTo;
        this.dishTos = dishTos;
        this.date = date;
    }

    public RestaurantTo getRestaurantTo() {
        return restaurantTo;
    }

    public List<DishTo> getDishTos() {
        return dishTos;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuTo menuTo = (MenuTo) o;
        return Objects.equals(id, menuTo.id) &&
                Objects.equals(restaurantTo.id, menuTo.restaurantTo.id) &&
                Objects.equals(dishTos, menuTo.dishTos) &&
                Objects.equals(date, menuTo.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantTo.id, dishTos, date);
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                ", id=" + id +
                ", restaurant name='" + restaurantTo.name + '\'' +
                ", dishTos=" + Arrays.toString(dishTos.toArray()) +
                ", date=" + date +
                '}';
    }
}

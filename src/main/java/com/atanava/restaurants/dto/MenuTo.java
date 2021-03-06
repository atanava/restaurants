package com.atanava.restaurants.dto;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MenuTo extends AbstractBaseTo {

    @NotNull
    private final Integer restaurantId;

    private final List<DishTo> dishTos;

    @FutureOrPresent
    private final LocalDate date;

    @ConstructorProperties({"id", "restaurantId", "dishTos", "date"})
    public MenuTo(Integer id, Integer restaurantId, List<DishTo> dishTos, LocalDate date) {
        super(id);
        this.restaurantId = restaurantId;
        this.dishTos = dishTos;
        this.date = date;
    }

    public Integer getRestaurantId() {
        return restaurantId;
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
                Objects.equals(restaurantId, menuTo.restaurantId) &&
                Objects.equals(dishTos, menuTo.dishTos) &&
                Objects.equals(date, menuTo.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurantId, dishTos, date);
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                ", id=" + id +
                ", restaurantId='" + restaurantId + '\'' +
                ", dishTos=" + Arrays.toString(dishTos.toArray()) +
                ", date=" + date +
                '}';
    }
}

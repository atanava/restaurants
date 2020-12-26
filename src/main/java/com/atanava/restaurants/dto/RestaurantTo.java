package com.atanava.restaurants.dto;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends AbstractNamedTo {

    private final MenuTo todayMenuTo;

    @ConstructorProperties({"id", "name", "todayMenuTo"})
    public RestaurantTo(Integer id, String name, MenuTo todayMenuTo) {
        super(id, name);
        this.todayMenuTo = todayMenuTo;
    }

    public MenuTo getTodayMenuTo() {
        return todayMenuTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTo restaurantTo = (RestaurantTo) o;

        return Objects.equals(id, restaurantTo.id) &&
                Objects.equals(name, restaurantTo.name) &&
                Objects.equals(todayMenuTo, restaurantTo.todayMenuTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todayMenuTo);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", todayMenuTo=" + todayMenuTo +
                '}';
    }
}

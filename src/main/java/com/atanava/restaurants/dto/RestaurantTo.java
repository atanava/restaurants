package com.atanava.restaurants.dto;

import com.atanava.restaurants.model.Menu;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends AbstractNamedTo {

    private final Menu todayMenu;

    private final int votesQty;

    @ConstructorProperties({"id", "name", "todayMenu", "votesQty"})
    public RestaurantTo(Integer id, String name, Menu todayMenu, int votesQty) {
        super(id, name);
        this.todayMenu = todayMenu;
        this.votesQty = votesQty;
    }

    public Menu getTodayMenu() {
        return todayMenu;
    }

    public int getVotesQty() {
        return votesQty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTo restaurantTo = (RestaurantTo) o;

        return Objects.equals(id, restaurantTo.id) &&
                Objects.equals(name, restaurantTo.name) &&
                Objects.equals(votesQty, restaurantTo.votesQty) &&
                Objects.equals(todayMenu, restaurantTo.todayMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todayMenu, votesQty);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "todayMenu=" + todayMenu +
                ", votesQty=" + votesQty +
                '}';
    }
}

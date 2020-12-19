package com.atanava.restaurants.dto;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class RestaurantTo extends AbstractNamedTo {

    private final MenuTo todayMenuTo;

    private final int votesCount;

    @ConstructorProperties({"id", "name", "todayMenuTo", "votesCount"})
    public RestaurantTo(Integer id, String name, MenuTo todayMenuTo, int votesCount) {
        super(id, name);
        this.todayMenuTo = todayMenuTo;
        this.votesCount = votesCount;
    }

    public MenuTo getTodayMenuTo() {
        return todayMenuTo;
    }

    public int getVotesCount() {
        return votesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTo restaurantTo = (RestaurantTo) o;

        return Objects.equals(id, restaurantTo.id) &&
                Objects.equals(name, restaurantTo.name) &&
                Objects.equals(todayMenuTo, restaurantTo.todayMenuTo) &&
                Objects.equals(votesCount, restaurantTo.votesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todayMenuTo, votesCount);
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", todayMenuTo=" + todayMenuTo +
                ", votesCount=" + votesCount +
                '}';
    }
}

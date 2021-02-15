package com.atanava.restaurants.dto;

import javax.validation.constraints.NotNull;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.util.Objects;

public class VoteTo extends AbstractBaseTo {

    private final LocalDate date;

    @NotNull
    private final Integer userId;

    @NotNull
    private final Integer restaurantId;

    @ConstructorProperties({"id", "userId", "restaurantId", "date"})
    public VoteTo(Integer id, Integer userId, Integer restaurantId, LocalDate date) {
        super(id);
        this.date = date;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteTo voteTo = (VoteTo) o;
        return Objects.equals(id, voteTo.id) &&
                Objects.equals(date, voteTo.date) &&
                Objects.equals(userId, voteTo.userId) &&
                Objects.equals(restaurantId, voteTo.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, userId, restaurantId);
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                ", id=" + id +
                ", date=" + date +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}

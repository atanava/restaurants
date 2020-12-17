package com.atanava.restaurants.dto;

import java.util.Objects;

public class VoteTo extends AbstractBaseTo {

    private final Integer userId;

    private final Integer restaurantId;

    public VoteTo(Integer id, Integer userId, Integer restaurantId) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
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
                Objects.equals(userId, voteTo.userId) &&
                Objects.equals(restaurantId, voteTo.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, restaurantId);
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                ", id=" + id +
                "userId=" + userId +
                ", restaurantId=" + restaurantId +
                '}';
    }
}

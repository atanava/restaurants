package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Vote.GET, query = "SELECT v FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.BY_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.BY_DATE, query = "SELECT v FROM Vote v WHERE v.date=:date ORDER BY COUNT (v.restaurant.votes)"),
        @NamedQuery(name = Vote.BY_REST_AND_DATE, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.date=:date"),
})

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "unique_user_id_date_idx")})
public class Vote extends AbstractBaseEntity {

    public static final String GET = "Vote.get";
    public static final String BY_USER = "Vote.getAllByUser";
    public static final String BY_RESTAURANT = "Vote.getAllByRestaurant";
    public static final String BY_DATE = "Vote.getAllByDate";
    public static final String BY_REST_AND_DATE = "Vote.getByRestAndDate";

    @Column(name = "date", columnDefinition = "date default current_date",  nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Vote() {
    }

    public Vote(Integer id, LocalDate date, Restaurant restaurant, User user) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

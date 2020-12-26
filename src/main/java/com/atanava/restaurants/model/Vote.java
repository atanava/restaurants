package com.atanava.restaurants.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Vote.BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.BY_USER_AND_DATE, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date"),
        @NamedQuery(name = Vote.BY_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId ORDER BY v.date DESC"),
        @NamedQuery(name = Vote.BY_DATE, query = "SELECT v FROM Vote v WHERE v.date=:date"),
        @NamedQuery(name = Vote.BY_REST_AND_DATE, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId AND v.date=:date"),
        @NamedQuery(name = Vote.BY_USER_AND_REST, query = "SELECT v FROM Vote v WHERE v.user.id=:userId AND v.restaurant.id=:restaurantId"),
        @NamedQuery(name = Vote.UPDATE, query = "UPDATE Vote v SET v.restaurant=:restaurant WHERE v.id=:id AND v.user.id=:userId"),
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.DELETE_BY_USER_AND_DATE, query = "DELETE FROM Vote v WHERE v.user.id=:userId AND v.date=:date"),
        @NamedQuery(name = Vote.DELETE_ALL_BY_REST, query = "DELETE FROM Vote v WHERE v.restaurantId=:restaurantId"),
})

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "unique_user_id_date_idx")})
public class Vote extends AbstractBaseEntity {

    public static final String BY_USER = "Vote.getAllByUser";
    public static final String BY_USER_AND_DATE = "Vote.getByUserAndDate";
    public static final String BY_RESTAURANT = "Vote.getAllByRestaurant";
    public static final String BY_DATE = "Vote.getAllByDate";
    public static final String BY_REST_AND_DATE = "Vote.getByRestAndDate";
    public static final String BY_USER_AND_REST = "Vote.getAllByUserAndRest";
    public static final String UPDATE = "Vote.update";
    public static final String DELETE = "Vote.delete";
    public static final String DELETE_BY_USER_AND_DATE = "Vote.deleteByUserAndDate";
    public static final String DELETE_ALL_BY_REST = "Vote.deleteAllByRestaurant";

    @Column(name = "date", columnDefinition = "date default current_date",  nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(targetEntity = Restaurant.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference
    private Restaurant restaurant;

//    https://stackoverflow.com/questions/6311776/hibernate-foreign-keys-instead-of-entities
    @Column(name = "restaurant_id", insertable = false, updatable = false)
    private Integer restaurantId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @JsonBackReference
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

    public Vote() {
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date) {
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

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

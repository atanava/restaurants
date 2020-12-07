package com.atanava.restaurants.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

@NamedQueries({
        @NamedQuery(name = Restaurant.GET_WITH, query = "SELECT r FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractNamedEntity {
    public static final String GET_WITH = "Restaurant.getWith*";
    public static final String DELETE = "Restaurant.Delete";

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Menu> menus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName(), r.getDishes(), r.getMenus(), r.getVotes());
    }

    public Restaurant(Integer id, String name) {
        this(id, name, emptyList(), emptySet(), emptySet());
    }

    public Restaurant(Integer id, String name, List<Dish> dishes, Set<Menu> menus, Set<Vote> votes) {
        super(id, name);
        this.dishes = dishes;
        this.menus = menus;
        this.votes = votes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
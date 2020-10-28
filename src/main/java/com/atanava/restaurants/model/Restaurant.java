package com.atanava.restaurants.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    private Set<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private Set<Vote> votes;

    public Restaurant() {
    }

    // for tests
    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, Set<Menu> menus, Set<Vote> votes) {
        super(id, name);
//        this.dishes = dishes;
        this.votes = votes;
    }


//    public Set<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(Set<Dish> dishes) {
//        this.dishes = dishes;
//    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
}
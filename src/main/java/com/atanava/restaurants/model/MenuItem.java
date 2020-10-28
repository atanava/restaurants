package com.atanava.restaurants.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "menus")
public class MenuItem extends AbstractNamedEntity {

    @Column(name = "date", columnDefinition = "date default current_date", nullable = false)
    @NotNull
    Date date = new Date();

    @Column(name = "price", nullable = false)
    @NotNull
    private Integer price;

    public MenuItem() {
    }

    public MenuItem(Integer id, String name, Date date, Integer price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

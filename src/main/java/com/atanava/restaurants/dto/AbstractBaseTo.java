package com.atanava.restaurants.dto;

import com.atanava.restaurants.HasId;

import javax.validation.constraints.NotNull;

public abstract class AbstractBaseTo implements HasId {

    @NotNull
    protected Integer id;

    public AbstractBaseTo() {
    }

    public AbstractBaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}

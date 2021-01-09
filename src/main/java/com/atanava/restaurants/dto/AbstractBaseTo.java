package com.atanava.restaurants.dto;

import com.atanava.restaurants.HasId;

public abstract class AbstractBaseTo implements HasId {

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

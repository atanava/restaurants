package com.atanava.restaurants.dto;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

public abstract class NamedTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml(whitelistType = NONE) // https://stackoverflow.com/questions/2147958/how-do-i-prevent-people-from-doing-xss-in-spring-mvc/40644276#40644276
    protected String name;

    public NamedTo() {
    }

    public NamedTo(String name) {
        this.name = name;
    }

    public NamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

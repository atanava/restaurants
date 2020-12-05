package com.atanava.restaurants.dto;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

public class UserTo extends NamedTo {

    @Email
    @NotBlank
    @Size(max = 254) // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @SafeHtml(whitelistType = NONE) // https://stackoverflow.com/questions/2147958/how-do-i-prevent-people-from-doing-xss-in-spring-mvc/40644276#40644276
    private String email;

    @NotBlank
    @Size(min = 5, max = 32)
    private String password;

}

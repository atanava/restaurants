package com.atanava.restaurants.dto;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Objects;

import static org.hibernate.validator.constraints.SafeHtml.WhiteListType.NONE;

public class UserTo extends AbstractNamedTo {

    @Email
    @NotBlank
    @Size(max = 254) // https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @SafeHtml(whitelistType = NONE) // https://stackoverflow.com/questions/2147958/how-do-i-prevent-people-from-doing-xss-in-spring-mvc/40644276#40644276
    private String email;

    @NotBlank
    @Size(min = 5, max = 32)
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id, name);
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTo userTo = (UserTo) o;
        return Objects.equals(id, userTo.id) &&
                Objects.equals(name, userTo.name) &&
                Objects.equals(email, userTo.email) &&
                Objects.equals(password, userTo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password);
    }

    @Override
    public String toString() {
        return "UserTo{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

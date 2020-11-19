package com.atanava.restaurants.model;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@NamedQueries({
//        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
//        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
//        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email"),
//        @NamedQuery(name = User.BY_ID, query = "SELECT u FROM User u WHERE u.id=?1"),
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity {

//    public static final String DELETE = "User.delete";
//    public static final String BY_EMAIL = "User.getByEmail";
//    public static final String ALL_SORTED = "User.getAllSorted";
//    public static final String BY_ID = "User.getById";

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    //    https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address
    @Size(max = 254)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registered = new Date();

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("date DESC")
    private List<Vote> votes;

    public User() {
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(),
                user.getRegistered(), user.isEnabled(), user.getRoles(), user.getVotes());
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, new Date(), true, EnumSet.of(role, roles), Collections.emptyList());
    }

    public User(Integer id, String name, String email, String password, Date registered,
                boolean enabled, Set<Role> roles, List<Vote> votes) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
        this.enabled = enabled;
        setRoles(roles);
        setVotes(votes);
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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email=" + email +
                ", name=" + name +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }

}


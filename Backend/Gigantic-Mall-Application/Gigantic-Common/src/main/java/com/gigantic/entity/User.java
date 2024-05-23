package com.gigantic.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends IdBasedEntity {

    //Attributes

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 45, nullable = false, unique = false)
    private String firstName;

    @Column(name = "last_name", length = 45, nullable = false, unique = false)
    private String lastName;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "photos", length = 64, nullable = true)
    private String photos;

    private boolean enabled;

    //Constructor

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
        //Default Constructor
    }

    public User(String email, String firstName, String lastName, String password, String photos, boolean enabled) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.photos = photos;
        this.enabled = enabled;
    }

    //Getter & Setter
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhotos() {
        return photos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return enabled == user.enabled && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && Objects.equals(photos, user.photos) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, password, photos, enabled, roles);
    }
}

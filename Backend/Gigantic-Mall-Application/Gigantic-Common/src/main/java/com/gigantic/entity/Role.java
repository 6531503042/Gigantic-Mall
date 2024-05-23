package com.gigantic.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role extends IdBasedEntity{

    //Attributes

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    //Constructor
    public Role() {
        //Default Constructor
    }

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //Getter & Setter

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name) && Objects.equals(description, role.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

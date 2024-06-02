package com.gigantic.entity;

import com.gigantic.Mapper.IdBasedEntity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "categories")
public class Category extends IdBasedEntity {

    @Column(length = 128, nullable = false, unique = true)
    private String name;


    @Column(length = 64, nullable = false, unique = true)
    private  String alias;

    @Column(length = 128, nullable = false)
    private String image;

    @Transient
    private boolean hasChildren;

    private boolean enabled;

    @Column(name = "all_parent_ids", length = 256, nullable = true)
    private String allParentIds;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy("name asc")
    private Set<Category> children = new HashSet<>();

    // Default Constructor
    public Category() {
        this.image = "default.png";
    }

    // Constructor for existing ID
//    public Category(Long id) {
//        this.id = id;
//    }

    // Create Root Category (Constructor)
    public Category(String name) {
        this.name = name;
        this.alias = name;
        this.image = "default.png";
    }

    // Create Sub Category (Constructor)
    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
    }

    // Full Constructor for testing or other purposes
//    public Category(Integer id, String name, String alias) {
//        super();
//        this.id = Long.valueOf(id);
//        this.name = name;
//        this.alias = alias;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String toString() {
        return this.name;
    }

    public String getAllParentIds() {
        return allParentIds;
    }

    public void setAllParentIds(String allParentIds) {
        this.allParentIds = allParentIds;
    }


}

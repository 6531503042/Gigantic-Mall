package com.gigantic.entity;

import com.gigantic.Mapper.IdBasedEntity;
import lombok.Setter;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends IdBasedEntity {

    @Column(length = 128, nullable = false, unique = false)
    private String name;

    @Column(length = 64, nullable = false, unique = false)
    private String alias;

    @Column(length = 128, nullable = false)
    private String image;

    private boolean enabled;

    @Column(name = "all_parent_ids", length = 256, nullable = true)
    private String allParentIDs;

    // Many categories can have one parent category
    @ManyToOne
    @JoinColumn(name = "parent_id")
//    @JsonManagedReference // Breaks the cycle for parent reference
    private Category parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("name asc")
//    @JsonBackReference // Breaks the cycle for child reference
    private Set<Category> children = new HashSet<>();

    @Setter
    @Transient
    private String parentName;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
        this.alias = name;
        this.image = "default.png";
    }

    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
    }

    public Category(Long id, String name, String alias) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    public Category(Long id, String name, String alias, String image, boolean enabled, String allParentIDs, Category parent, Set<Category> children) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.image = image;
        this.enabled = enabled;
        this.allParentIDs = allParentIDs;
        this.parent = parent;
        this.children = children;
    }

    public static Category copyIdAndName(Category category) {
        Category copy = new Category();
        copy.setId(category.getId());
        copy.setName(category.getName());
        return copy;
    }

    public static Category copyIdAndName(Long id,String name) {
        Category copy = new Category();
        copy.setId(id);
        copy.setName(name);
        return copy;
    }

    public static Category copyFull(Category category) {
        Category copy = new Category();
        copy.setId(category.getId());
        copy.setName(category.getName());
        copy.setAlias(category.getAlias());
        copy.setImage(category.getImage());
        copy.setHasChildren(category.isHasChildren());
        copy.setEnabled(category.isEnabled());
        copy.setAllParentIDs(category.getAllParentIDs());
        copy.setParent(category.getParent());

        return copy;
    }

    public static Category copyFull(Category category, String name) {
        Category copyCategory = Category.copyFull(category);
        copyCategory.setName(name);

        return copyCategory;
    }

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

    public Category setParent(Category parent) {
        this.parent = parent;
        return parent;
    }

    public String getParentName() {
        return parentName;
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

    @Transient
    private boolean hasChildren;

    @Override
    public String toString() {
        return this.name;
    }

    public String getAllParentIDs() {
        return allParentIDs;
    }

    public void setAllParentIDs(String allParentIDs) {
        this.allParentIDs = allParentIDs;
    }


}

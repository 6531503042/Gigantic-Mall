package com.gigantic.DTO;

public class SubCategoryRequest {

    //Attributes
    private String name;
    private Long parentId;
    private String alias;
    private String image;

    //Constructor
    public SubCategoryRequest() {
        //Default constructor
    }

    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
}

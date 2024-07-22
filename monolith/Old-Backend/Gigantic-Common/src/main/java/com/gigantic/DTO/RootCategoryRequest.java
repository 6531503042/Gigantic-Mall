package com.gigantic.DTO;

public class RootCategoryRequest {

    //Atrributes
    private String name;
    private String alias;
    private String image;

    //Constructor
    public RootCategoryRequest() {
        //Default constructor
    }

    //Getters and Setters
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
}

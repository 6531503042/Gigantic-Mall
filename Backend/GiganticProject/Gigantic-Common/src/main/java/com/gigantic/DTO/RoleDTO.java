package com.gigantic.DTO;

import javax.validation.constraints.NotEmpty;

public class RoleDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    // Getters and setters...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

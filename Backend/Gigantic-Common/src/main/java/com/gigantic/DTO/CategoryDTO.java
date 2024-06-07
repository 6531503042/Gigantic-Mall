package com.gigantic.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String alias;
    private String image;
    private boolean enabled;
    private String allParentIDs;
    private Long parentId;

    @JsonBackReference
    private Set<CategoryDTO> children = new HashSet<>();
    private boolean hasChildren;

    public boolean getEnabled() {
        return enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}

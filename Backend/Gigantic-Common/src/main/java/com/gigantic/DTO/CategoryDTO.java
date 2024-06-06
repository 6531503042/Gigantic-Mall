package com.gigantic.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String allParentId;
    private Long parentId;
    private Set<CategoryDTO> children;
    private boolean hasChildren;
}

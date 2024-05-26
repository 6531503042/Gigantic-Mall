package com.gigantic.Mapper;

import com.gigantic.DTO.RoleDTO;
import com.gigantic.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }

        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());

        return role;
    }

    public static RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        roleDTO.setDescription(role.getDescription());

        return roleDTO;
    }
}
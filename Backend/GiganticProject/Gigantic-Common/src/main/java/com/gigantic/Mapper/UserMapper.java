package com.gigantic.Mapper;

import com.gigantic.DTO.UserDTO;
import com.gigantic.entity.User;
import com.gigantic.entity.Role;

import java.util.stream.Collectors;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhotos(userDTO.getPhotos());
        user.setEnabled(userDTO.isEnabled());

        if (userDTO.getRoles() != null) {
            user.setRoles(
                    userDTO.getRoles().stream()
                            .map(RoleMapper::toEntity)
                            .collect(Collectors.toSet())
            );
        }

        return user;
    }

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhotos(user.getPhotos());
        userDTO.setEnabled(user.isEnabled());

        if (user.getRoles() != null) {
            userDTO.setRoles(
                    user.getRoles().stream()
                            .map(RoleMapper::toDTO)
                            .collect(Collectors.toSet())
            );
        }

        return userDTO;
    }
}

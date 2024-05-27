package com.gigantic.admin.Service;

import com.gigantic.DTO.UserDTO;
import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;

import java.util.List;

public interface UserService {
    String encoderPassword(String rawPassword);


    User saveUser(UserDTO userDTO) throws DuplicateUserException;

    List<User> getAllUser();

    List<UserDTO> getAllUsers();

    List<Role> listRoles();
}

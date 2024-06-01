package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;

import java.util.List;

public interface UserService {
    String encoderPassword(String rawPassword);


    User saveUser(User user) throws DuplicateUserException;

    List<User> getAllUser();

    List<User> getAllUsers();

    List<Role> listRoles();

    User getUserById(Long id);
}

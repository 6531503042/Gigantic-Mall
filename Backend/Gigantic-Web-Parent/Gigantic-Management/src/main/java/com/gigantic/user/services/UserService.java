package com.gigantic.user.services;

import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;

import java.util.List;

public interface UserService {

    User getByEmail(String email);

    String encoderPassword(String rawPassword);

    User saveUser(User user) throws DuplicateUserException;

//    List<User> getAllUsers();

    List<User> getAllUsers(String firstName, String lastName, String email, String role, String sortField, String sortDirection);

    List<Role> listRoles();

    User getUserById(Long id);

    User updateUser(Long id, User userDetails);

    User get(Long id);

    String deleteById(Long id);

    void updateUserEnabledStatus(Long id, boolean enabled);

    boolean isEmailUnique(Long id, String email);
}

package com.gigantic.admin.Service;

import com.gigantic.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();
}

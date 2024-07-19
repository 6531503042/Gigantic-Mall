package com.gigantic.user.services;


import com.gigantic.user.dto.UserInfoDTO;
import com.gigantic.user.model.User;

public interface UserService {
    User getUserById(int id);

    UserInfoDTO getUserDTObyId(int id);
}

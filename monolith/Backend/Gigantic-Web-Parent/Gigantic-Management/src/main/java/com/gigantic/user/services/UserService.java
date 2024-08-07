package com.gigantic.user.services;


import com.gigantic.common.enumeration.RoleEnum;
import com.gigantic.user.dto.UserCreatedDTO;
import com.gigantic.user.dto.UserInfoDTO;
import com.gigantic.user.model.User;

public interface UserService {
    User getUserById(int id);

    UserInfoDTO getUserDTObyId(int id);

    UserInfoDTO createUser(UserCreatedDTO dto, int currentUserId, RoleEnum newUserRole);

    UserInfoDTO updateUser(int id, UserCreatedDTO dto);
}

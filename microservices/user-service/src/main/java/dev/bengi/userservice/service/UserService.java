package dev.bengi.userservice.service;

import dev.bengi.userservice.dto.UserCreatedDTO;
import dev.bengi.userservice.dto.UserRetrieveDTO;
import dev.bengi.userservice.enumeration.RoleEnum;
import dev.bengi.userservice.model.User;

public interface UserService {
    User getUserById(int id);

    UserRetrieveDTO getUserDTObyId(int id);

    UserRetrieveDTO createUser (UserCreatedDTO dto, int currentUserId, RoleEnum newUserRole);
}

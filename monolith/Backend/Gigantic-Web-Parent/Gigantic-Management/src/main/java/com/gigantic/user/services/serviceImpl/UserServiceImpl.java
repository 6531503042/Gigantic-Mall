package com.gigantic.user.services.serviceImpl;


import com.gigantic.common.enumeration.RoleEnum;
import com.gigantic.user.dto.UserCreatedDTO;
import com.gigantic.user.model.User;
import com.gigantic.user.dto.UserInfoDTO;
import com.gigantic.user.services.RoleService;
import com.gigantic.user.services.UserService;
import com.gigantic.user.repository.RoleRepository;
import com.gigantic.user.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService, RoleService roleService1) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService1;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID: %d not found", id)));
    }

    @Override
    public UserInfoDTO getUserDTObyId(int id) {
        var user = getUserById(id);
        return new UserInfoDTO(user.id(), user.firstName(), user.lastName(), user.email(), user.phoneNumber());
    }

    @Override
    public UserInfoDTO createUser(UserCreatedDTO dto, int currentUserId, RoleEnum newUserRole) {

        // Get the maximum role of the current user
        var currentUserMaxRole = roleService.getMaxRoleForUser(currentUserId);

        // Check if the user is allowed to create a user with the new role
        if (newUserRole.getLevel() > currentUserMaxRole.get().getLevel()) {
            throw new IllegalArgumentException(String.format("User with ID: %d cannot create a user with role: %s", currentUserId, newUserRole));
        }

        //Create User
        var preparedUser = new User(
                null,
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                dto.dateCreated() != null ? dto.dateCreated() : new Date(),
                dto.phoneNumber(),
                true
        );
        var newUser = userRepository.save(preparedUser);


        //Bind Role
        var userRole = roleService.bindingNewUser(
                newUser.id(),
                newUserRole
        );


        //Log
        logger.info("Created new user with ID {}", newUser.id());

        //Return UserInfoDTO
        return new UserInfoDTO(newUser.id(), newUser.firstName(), newUser.lastName(), newUser.email(), newUser.phoneNumber());
    }

    @Override
    public UserInfoDTO updateUser(int id, UserCreatedDTO dto) {
        var user = getUserById(id);
        var preparedUser = new User(
                user.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                dto.dateCreated() != null ? dto.dateCreated() : new Date(),
                dto.phoneNumber(),
                true
        );
        var updatedUser = userRepository.save(preparedUser);
        logger.info("Updated user with ID {}", updatedUser.id());
        return new UserInfoDTO(updatedUser.id(), updatedUser.firstName(), updatedUser.lastName(), updatedUser.email(), updatedUser.phoneNumber());
    }

    public Page<User> getUsersByFirstName(String firstName, Pageable pageable) {
        return userRepository.findByFirstName(firstName, pageable);
    }
}

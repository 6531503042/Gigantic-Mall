package dev.bengi.userservice.service.impl;

import dev.bengi.userservice.dto.UserCreatedDTO;
import dev.bengi.userservice.dto.UserRetrieveDTO;
import dev.bengi.userservice.model.User;
import dev.bengi.userservice.repository.RoleRepository;
import dev.bengi.userservice.repository.UserRepository;
import dev.bengi.userservice.service.RoleService;
import dev.bengi.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import dev.bengi.userservice.enumeration.RoleEnum;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id " + id + " not found")));
    }

    @Override
    public UserRetrieveDTO getUserDTObyId(int id) {
        var user = getUserById(id);
        return new UserRetrieveDTO(
                user.id(),
                user.firstName(),
                user.lastName(),
                user.email(),
                user.phoneNumber()
        );
    }

    //Created user + login credential + wallet + roleMaximumAuthorization
    @Transactional
    @Override
    public UserRetrieveDTO createUser(UserCreatedDTO dto, int currentUserId, RoleEnum newUserRole) {

        var currentUserMaximumRoleAuthorization = roleService.getMaxRoleForAutorizationUser(currentUserId);

        if (newUserRole.getLevel() > currentUserMaximumRoleAuthorization.get().getLevel()) {
            throw new IllegalArgumentException("User is not authorized to create new user");
        }

        //Create User
        var preparedUser = new User(
                null,
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.password(),
                dto.createdAt() != null ? dto.createdAt() : new Date(),
                dto.phoneNumber(),
                true
        );
        var newUser = userRepository.save(preparedUser);

        //Bind Role
        var userRole = roleService.bindingNewUser(
                newUser.id(),
                newUserRole
        );

        //logging
        logger.info("Created new user: {}", newUser);
        logger.info("Created new user role: {}", userRole);

        return new UserRetrieveDTO(
                newUser.id(),
                newUser.firstName(),
                newUser.lastName(),
                newUser.email(),
                newUser.phoneNumber()
        );
    }



}

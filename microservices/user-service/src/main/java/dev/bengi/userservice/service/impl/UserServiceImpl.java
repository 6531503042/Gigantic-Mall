package dev.bengi.userservice.service.impl;

import dev.bengi.userservice.dto.UserCreatedDTO;
import dev.bengi.userservice.dto.UserRetrieveDTO;
import dev.bengi.userservice.model.User;
import dev.bengi.userservice.repository.RoleRepository;
import dev.bengi.userservice.repository.UserRepository;
import dev.bengi.userservice.service.RoleService;
import dev.bengi.userservice.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import dev.bengi.userservice.enumeration.RoleEnum;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public User getUserById(int id) {
        logger.info("Fetching user with id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %d not found", id)));
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

    @Transactional
    @Override
    public UserRetrieveDTO createUser(UserCreatedDTO dto, int currentUserId, RoleEnum newUserRole) {
        logger.info("Creating user with email: {}", dto.email());

        var currentUserMaximumRoleAuthorization = roleService.getMaxRoleForAutorizationUser(currentUserId);

        if (newUserRole.getLevel() > currentUserMaximumRoleAuthorization.get().getLevel()) {
            logger.error("User is not authorized to create new user with role: {}", newUserRole);
            throw new IllegalArgumentException("User is not authorized to create new user");
        }

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

        var userRole = roleService.bindingNewUser(newUser.id(), newUserRole);

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

    @Override
    public Page<User> getUsersByFirstName(String keyword, Pageable pageable) {
        logger.info("Searching users with first name containing: {}", keyword);
        return userRepository.findByFirstNameContaining(keyword, pageable);
    }

    @Transactional
    @Override
    public boolean deleteUserById(int id) {
        logger.info("Deleting user with id: {}", id);
        var user = getUserById(id);
        userRepository.delete(user);
        logger.info("User with id: {} deleted successfully", id);
        return true;
    }
}
package com.gigantic.user.services;

import com.gigantic.common.enumeration.RoleEnum;
import com.gigantic.user.model.Role;
import com.gigantic.user.model.UserRole;
import com.gigantic.user.model.User;
import org.slf4j.Logger;
import com.gigantic.user.repository.RoleRepository;
import com.gigantic.user.repository.UserRoleRepository;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleService {


    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

    //Constructor Injection
    private final RoleRepository roleRepo;
    private final UserRoleRepository userRoleRepo;

    public RoleService(RoleRepository roleRepo, UserRoleRepository userRoleRepo) {
        this.roleRepo = roleRepo;
        this.userRoleRepo = userRoleRepo;
    }

    public Iterable<com.gigantic.entity.Role> getAllRole() {
        var availableRole = roleRepo.findAll();
        logger.info("availableRole: {}", availableRole);
        return availableRole;
    }

    public UserRole bindingNewUser(int id, RoleEnum role) {
        AggregateReference<User, Integer> userId = AggregateReference.to(id);
        AggregateReference<Role, Integer> roleId = AggregateReference.to(role.getId());
        UserRole userRole = new UserRole(id, userId, roleId);
        userRoleRepo.save(userRole);
        logger.info("Bound new user with ID {} to role {}", userId, role);
        return userRole;
    }

    public void deleteRoleById(int userId) {
        var userRole = userRoleRepo.findOneByUserId(AggregateReference.to(userId))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role for userId: %d not found", userId)));
        userRoleRepo.delete(userRole);
        logger.info("Deleted role binding for user with ID {}", userId);
    }
}
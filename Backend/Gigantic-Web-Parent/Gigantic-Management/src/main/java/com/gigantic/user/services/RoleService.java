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
import java.util.Comparator;
import java.util.Optional;

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

    public Iterable<Role> getAllRole() {
        var availableRole = roleRepo.findAll();
        logger.info("availableRole: {}", availableRole);
        return availableRole;
    }

    //Binding new user to role
    public UserRole bindingNewUser(int userId, RoleEnum role) {
        AggregateReference<User, Integer> userRef = AggregateReference.to(userId);
        AggregateReference<Role, Integer> roleRef = AggregateReference.to(role.getId());
        UserRole userRole = new UserRole(null, userRef, roleRef);
        userRoleRepo.save(userRole);
        logger.info("Bound new user with ID {} to role {}", userId, role);
        return userRole;
    }

    public Optional<RoleEnum> getMaxRoleForUser(int userId) {
        var roles = userRoleRepo.findAllByUserId(AggregateReference.to(userId));
        if (roles.isEmpty()) {
            return Optional.empty();
        }
        return roles.stream()
                .map(userRole -> {
                    Integer roleId = userRole.roleId().getId();
                    Role role = roleRepo.findById(roleId)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("Role with ID: %d not found", roleId)));
                    return RoleEnum.valueOf(role.name().toUpperCase());
                })
                .max(Comparator.comparingInt(RoleEnum::getLevel));
    }

    public boolean canAssignRole(int userId, RoleEnum newRole) {
        var maxRole = getMaxRoleForUser(userId);
        return maxRole.map(role -> newRole.getLevel() >= role.getLevel()).orElse(false);
    }

    public void deleteRoleById(int userId) {
        var userRole = userRoleRepo.findOneByUserId(AggregateReference.to(userId))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Role for userId: %d not found", userId)));
        userRoleRepo.delete(userRole);
        logger.info("Deleted role binding for user with ID {}", userId);
    }
}
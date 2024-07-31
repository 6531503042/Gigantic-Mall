package dev.bengi.userservice.service;

import dev.bengi.userservice.enumeration.RoleEnum;
import dev.bengi.userservice.model.Role;
import dev.bengi.userservice.model.User;
import dev.bengi.userservice.model.UserRole;
import dev.bengi.userservice.repository.RoleRepository;
import dev.bengi.userservice.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.Optional;

@Service
public class RoleService {

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);


    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    // TODO: Implement role management
    public Iterable<Role> getAllRoles() {
        var available = roleRepository.findAll();
        logger.info("Available roles: {}", available);
        return available;
    }

    // TODO: Implement role management
    @Transactional
    public UserRole bindingNewUser(int userId, RoleEnum roleEnum) {
        AggregateReference<User, Integer> userRef = AggregateReference.to(userId);
        AggregateReference<Role, Integer> roleRef = AggregateReference.to(roleEnum.getId());
        UserRole userRole = new UserRole(null, userRef, roleRef);
        logger.info("Binding new user role: {}", userRole);
        return userRole;
    }

    // TODO: Implement role management
    @Transactional
    public void deleteUserRole(int userId, RoleEnum roleEnum) {
        AggregateReference<User, Integer> userRef = AggregateReference.to(userId);
        AggregateReference<Role, Integer> roleRef = AggregateReference.to(roleEnum.getId());
        var userRole = userRoleRepository.findByUserIdAndRoleId(userRef, roleRef)
                .orElseThrow(() -> new EntityNotFoundException("UserRole not found"));
        logger.info("Deleting user role: {}", userRole);
        userRoleRepository.delete(userRole);
    }

    // TODO: Implement role authorization management
    public Optional<RoleEnum> getMaxRoleForAutorizationUser(int userId) {
        var roles = userRoleRepository.findAllByUserId(AggregateReference.to(userId));

        //Checking if user has at least one role
        if (roles.isEmpty()) {
            return Optional.empty();
        }

        // Then get the max role
        return roles.stream()
                .map(userRole -> {
                    Integer roleId = userRole.roleId().getId();
                    Role role = roleRepository.findById(roleId)
                            .orElseThrow(() -> new EntityNotFoundException(String.format("Role with id " + roleId + " not found")));
                    // initial value to UPPERCASE
                    return RoleEnum.valueOf(role.name().toUpperCase());
                })
                // Comparator to get the max role
                .max(Comparator.comparingInt(RoleEnum::getLevel));
    }

    // TODO: Implement role authorization management
    public boolean canAssign(int userId, RoleEnum newRole) {
        var maxRole = getMaxRoleForAutorizationUser(userId);
        return maxRole.map(roleEnum -> roleEnum.getLevel() < newRole.getLevel()).orElse(true);
    }

    @Transactional
    public UserRole assignRoleToUser(int userId, RoleEnum newRole) {
        if (!canAssign(userId, newRole)) {
            throw new IllegalArgumentException("Cannot assign role: " + newRole);
        }
        AggregateReference<User, Integer> userRef = AggregateReference.to(userId);
        AggregateReference<Role, Integer> roleRef = AggregateReference.to(newRole.getId());
        UserRole userRole = new UserRole(null, userRef, roleRef);
        return userRoleRepository.save(userRole);
    }

}

package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Config.UserSpecificationConfig;
import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Exception.UserNotFoundException;
import com.gigantic.admin.Repository.RoleRepository;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.UserService;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public String encoderPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public User saveUser(@Valid User user) throws DuplicateUserException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("User with this email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers(String firstName, String lastName, String email, String role, String sortField, String sortDirection) {
        firstName = (firstName != null) ? firstName : "";
        lastName = (lastName != null) ? lastName : "";
        email = (email != null) ? email : "";
        role = (role != null) ? role : "";
        sortField = (sortField != null) ? sortField : "id";
        sortDirection = (sortDirection != null) ? sortDirection : "asc";

        Specification<User> specs = Specification.where(UserSpecificationConfig.hasFirstName(firstName))
                .and(UserSpecificationConfig.hasLastName(lastName))
                .and(UserSpecificationConfig.hasEmail(email))
                .and(UserSpecificationConfig.hasRole(role));

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        return userRepository.findAll(specs, sort);
    }



    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Couldn't find user with id: " + id));
    }


    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(userDetails.getId()).get();

        if (!user.getEmail().equals(userDetails.getEmail()) && userRepository.existsByEmail(userDetails.getEmail())) {
            throw new DuplicateUserException("Email already in use: " + userDetails.getEmail());
        }

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());

        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        if (userDetails.getRoles() != null && !userDetails.getRoles().isEmpty()) {
            Set<Role> newRoles = new HashSet<>();
            for (Role role : userDetails.getRoles()) {
                newRoles.add(roleRepository.findById(role.getId()).orElseThrow(() -> new IllegalArgumentException("Role not found")));
            }
            user.setRoles(newRoles);
        }

        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Couldn't find user with id: " + id));
    }

    @Override
    public String deleteById(Long id) {
        User user = get(id); // Fetch the user by id
        if (user == null) { // Check if the user exists
            throw new UserNotFoundException("Couldn't find user with id: " + id);
        } else {
            userRepository.deleteById(id); // Delete the user
        }
        return "User with id " + id + " has been deleted"; // Return a custom message
    }

    @Override
    public void updateUserEnabledStatus(Long id, boolean enabled) {
        try {
            userRepository.updateEnabledStatus(id, enabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isEmailUnique(Long id, String email) {
        User userByEmail =  userRepository.getUserByEmail(email);

        if (userByEmail == null) return true;

        boolean isCreatingNew = (id == null);

        if (isCreatingNew) {
            if (userByEmail != null) return false;

        } else {
            if (userByEmail.getId() != id) {
                return false;
            }
        }
        return true;
    }
}
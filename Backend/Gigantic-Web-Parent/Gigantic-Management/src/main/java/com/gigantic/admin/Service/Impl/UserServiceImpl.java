package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Exception.UserNotFoundException;
import com.gigantic.admin.Repository.RoleRepository;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.UserService;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;
import java.util.*;

@Service
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
    public String encoderPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

//    @Override
//    public User.java saveUser(User.java user) {
//        return userRepository.save(user);
//    }

    @Override
    public User saveUser(@Valid User user) throws DuplicateUserException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateUserException("User with this email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }

//    @Override
//    public List<User.java> getAllUser() {
//        return (List<User.java>) userRepository.findAll();
//    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Couldn't find user with id: " + id));
    }

//    public User get(Long id) {
//        try {
//            return userRepository.findById(id).get();
//        } catch (NoSuchElementException e) {
//            throw new UserNotFoundException("Couldn't find user with this id" + id );
//        }
//    }
//public User updateUser(Long id, User userDetails) {
//    User user = get(id);
//    user.setFirstName(userDetails.getFirstName());
//    user.setEmail(userDetails.getEmail());
//    user.setPassword(userDetails.getPassword());
//    // Update other fields as necessary
//    return userRepository.save(user);
// }


    public User updateUser(Long id, User userDetails) {
        User user = get(id);

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

    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Couldn't find user with id: " + id));
    }

    public String deleteById(Long id) {
        User user = get(id); // Fetch the user by id
        if (user == null) { // Check if the user exists
            throw new UserNotFoundException("Couldn't find user with id: " + id);
        } else {
            userRepository.deleteById(id); // Delete the user
        }
        return "User with id " + id + " has been deleted"; // Return a custom message
    }




}
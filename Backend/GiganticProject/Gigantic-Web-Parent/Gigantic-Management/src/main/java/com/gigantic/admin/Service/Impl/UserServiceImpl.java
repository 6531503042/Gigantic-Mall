package com.gigantic.admin.Service.Impl;

import com.gigantic.DTO.UserDTO;
import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Repository.RoleRepository;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.UserService;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
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
    public User saveUser(@Valid UserDTO userDTO) throws DuplicateUserException {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateUserException("User.java with this email already exists");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
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
    public List<UserDTO> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            // Use ModelMapper to convert User.java entity to UserDTO
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }


    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }
}
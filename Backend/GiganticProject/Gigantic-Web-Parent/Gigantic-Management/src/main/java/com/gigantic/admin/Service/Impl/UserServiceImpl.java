package com.gigantic.admin.Service.Impl;

import com.gigantic.DTO.UserDTO;
import com.gigantic.admin.Exception.DuplicateUserException;
import com.gigantic.admin.Repository.RoleRepository;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.UserService;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }

    @Override
    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateUserException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }

//    @Override
//    public List<User> getAllUser() {
//        return (List<User>) userRepository.findAll();
//    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            // Use ModelMapper to convert User entity to UserDTO
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
package com.gigantic.admin.Service.Impl;

import com.gigantic.admin.Repository.RoleRepository;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.admin.Service.UserService;
import com.gigantic.entity.Role;
import com.gigantic.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }
}
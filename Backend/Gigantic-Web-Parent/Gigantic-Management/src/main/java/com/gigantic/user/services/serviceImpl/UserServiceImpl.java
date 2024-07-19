package com.gigantic.user.services.serviceImpl;


import com.gigantic.user.model.User;
import com.gigantic.user.dto.UserInfoDTO;
import com.gigantic.user.services.UserService;
import com.gigantic.user.repository.RoleRepository;
import com.gigantic.user.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with ID: %d not found", id)));
    }

    @Override
    public UserInfoDTO getUserDTObyId(int id) {
        var user = getUserById(id);
        return new UserInfoDTO(user.id(), user.firstName(), user.lastName(), user.email(), user.phoneNumber());
    }
}

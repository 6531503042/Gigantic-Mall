package com.gigantic.admin.Service;

import com.gigantic.admin.Exception.DuplicateEmailException;
import com.gigantic.admin.Repository.UserRepository;
import com.gigantic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    // List All Users within --> Hibernate
    public List<User> listAll() {
        return (List<User>) UserRepository.findAll();
    }

    public User saveUser(User user) {
        try {
            encodePassword(user); // Encode Password to ensure the password is secure
            return UserRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Email address is already in use");
        }
    }
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}

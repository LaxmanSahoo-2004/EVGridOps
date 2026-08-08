package com.laxman.evgridops.service;

import com.laxman.evgridops.dto.RegisterRequestDTO;
import com.laxman.evgridops.entity.User;
import com.laxman.evgridops.exception.UserAlreadyExistsException;
import com.laxman.evgridops.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode((request.getPassword())));
        user.setRole(request.getRole());

        userRepository.save(user);
    }


}
package com.zephyr.api.service;

import com.zephyr.api.dto.RegisterRequestDTO;
import com.zephyr.api.entity.User;
import com.zephyr.api.exception.UserAlreadyExistsException;
import com.zephyr.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public User register(RegisterRequestDTO dto) {

        Optional<User> existingUser = repository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("email already exists");
        }
        User user = new User(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );

        return repository.save(user);
    }
}

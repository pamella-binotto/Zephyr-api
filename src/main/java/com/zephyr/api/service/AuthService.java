package com.zephyr.api.service;

import com.zephyr.api.dto.LoginRequestDTO;
import com.zephyr.api.dto.RegisterRequestDTO;
import com.zephyr.api.dto.response.UserResponseDTO;
import com.zephyr.api.entity.User;
import com.zephyr.api.exception.InvalidPasswordException;
import com.zephyr.api.exception.UserAlreadyExistsException;
import com.zephyr.api.exception.UserNotFoundException;
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

    public User login (LoginRequestDTO dto) {

        Optional<User> existingUser = repository.findByEmail(dto.getEmail());
        if (existingUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if(!dto.getPassword().equals(existingUser.get().getPassword())) {
            throw new InvalidPasswordException( "Incorrect password");
        }
        return existingUser.get();
    }

}

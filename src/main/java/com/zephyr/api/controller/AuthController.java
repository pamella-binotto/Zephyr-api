package com.zephyr.api.controller;


import com.zephyr.api.dto.LoginRequestDTO;
import com.zephyr.api.dto.RegisterRequestDTO;
import com.zephyr.api.dto.WeatherDataRequestDTO;
import com.zephyr.api.dto.response.UserResponseDTO;
import com.zephyr.api.entity.User;
import com.zephyr.api.entity.WeatherData;
import com.zephyr.api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {this.authService = authService;}



    @Operation(summary = "Create new register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO>createUser (@Valid @RequestBody RegisterRequestDTO dto) {

        User user = authService.register(dto);

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "Create new login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser (@Valid @RequestBody LoginRequestDTO dto) {

        User user = authService.login(dto);

        UserResponseDTO response = new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
        return ResponseEntity.ok(response);
    }

}


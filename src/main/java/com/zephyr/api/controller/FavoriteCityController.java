package com.zephyr.api.controller;


import com.zephyr.api.dto.FavoriteCityRequestDTO;
import com.zephyr.api.dto.response.FavoriteCityResponseDTO;
import com.zephyr.api.dto.response.UserResponseDTO;
import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.service.FavoriteCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class FavoriteCityController {

    private final FavoriteCityService favoriteCityService;

    public FavoriteCityController(FavoriteCityService favoriteCityService) {
        this.favoriteCityService = favoriteCityService;
    }

    @Operation(summary = "Create new favorite city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Favorite City successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @PostMapping("/favorite")

    public ResponseEntity<FavoriteCityResponseDTO> saveFavoriteCity(
            @RequestBody FavoriteCityRequestDTO dto) {

        FavoriteCity city = favoriteCityService.save(dto);

        FavoriteCityResponseDTO response = new FavoriteCityResponseDTO(
                city.getId(),
                city.getCityName()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}

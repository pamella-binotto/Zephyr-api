package com.zephyr.api.controller;


import com.zephyr.api.dto.FavoriteCityRequestDTO;
import com.zephyr.api.dto.response.FavoriteCityResponseDTO;
import com.zephyr.api.dto.response.FavoriteCityWeatherResponseDTO;
import com.zephyr.api.dto.response.UserResponseDTO;
import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.service.FavoriteCityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Operation(summary = "Get favorite city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite city found with successfully"),
            @ApiResponse(responseCode = "404", description = "Favorite city data not found")
    }
    )
    @GetMapping("favorite")

    public ResponseEntity<List<FavoriteCityResponseDTO>> findAllFavoriteCity() {

       List <FavoriteCity> cities = favoriteCityService.findAllByUser();

        List<FavoriteCityResponseDTO> response = new ArrayList<>();

        for (FavoriteCity city : cities) {
            response.add(
                    new FavoriteCityResponseDTO(
                            city.getId(),
                            city.getCityName()
                    )
            );

        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get weather from favorite city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite cities weather found successfully."),
            @ApiResponse(responseCode = "404", description = "Favorite cities not found")
    })
    @GetMapping("/favorite/weather")
    public ResponseEntity<List<FavoriteCityWeatherResponseDTO>>
    getFavoriteCitiesWeather() {

        List<FavoriteCityWeatherResponseDTO> response =
                favoriteCityService.getFavoriteCitiesWeather();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete favorite city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Favorite city deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Favorite city not found")
    })
    @DeleteMapping("/favorite/{id}")
    public ResponseEntity<Void> deleteFavoriteCity(@PathVariable Long id) {

        favoriteCityService.delete(id);

        return ResponseEntity.noContent().build();

    }
}

package com.zephyr.api;

import com.zephyr.api.dto.FavoriteCityRequestDTO;
import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.entity.User;
import com.zephyr.api.repository.FavoriteCityRepository;
import com.zephyr.api.repository.UserRepository;
import com.zephyr.api.service.FavoriteCityService;
import com.zephyr.api.service.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FavoriteCityServiceTest {

    private FavoriteCityService favoriteCityService;

    private FavoriteCityRepository favoriteCityRepository;
    private UserRepository userRepository;
    private WeatherDataService weatherDataService;

    @BeforeEach
    void setUp() {

        favoriteCityRepository = Mockito.mock(FavoriteCityRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        weatherDataService = Mockito.mock(WeatherDataService.class);

        favoriteCityService = new FavoriteCityService
                (favoriteCityRepository, userRepository, weatherDataService);
    }

    @Test
    void shouldSaveFavoriteCity() {

        String email = "pam@email.com";

        User user = new User(
                "Pamella",
                email,
                "1234"
        );

        FavoriteCityRequestDTO dto =
                new FavoriteCityRequestDTO();

        dto.setCity("Florianopolis");

        FavoriteCity favoriteCity = new FavoriteCity("Florianopolis", user);

        SecurityContext context = Mockito.mock(SecurityContext.class);

        Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(authentication.getPrincipal()).thenReturn(email);

        Mockito.when(context.getAuthentication()).thenReturn(authentication);

        SecurityContextHolder.setContext(context);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Mockito.when(favoriteCityRepository.save(Mockito.any())).thenReturn(favoriteCity);

        FavoriteCity result = favoriteCityService.save(dto);

        assertEquals("Florianopolis", result.getCityName());

    }
}


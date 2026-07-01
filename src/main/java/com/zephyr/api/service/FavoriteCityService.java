package com.zephyr.api.service;


import com.zephyr.api.dto.FavoriteCityEventDTO;
import com.zephyr.api.dto.FavoriteCityRequestDTO;
import com.zephyr.api.dto.response.CurrentWeatherResponseDTO;
import com.zephyr.api.dto.response.FavoriteCityWeatherResponseDTO;
import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.entity.User;
import com.zephyr.api.exception.FavoriteCityNotFoundException;
import com.zephyr.api.exception.UserNotFoundException;
import com.zephyr.api.messaging.RabbitMQProducer;
import com.zephyr.api.repository.FavoriteCityRepository;
import com.zephyr.api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class FavoriteCityService {

    private final FavoriteCityRepository repository;
    private final UserRepository userRepository;
    private final WeatherDataService weatherDataService;
    private final RabbitMQProducer rabbitMQProducer;

    public FavoriteCityService(FavoriteCityRepository repository,
                               UserRepository userRepository,
                               WeatherDataService weatherDataService,
                               RabbitMQProducer rabbitMQProducer) {

        this.repository = repository;
        this.userRepository = userRepository;
        this.weatherDataService = weatherDataService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public FavoriteCity save(FavoriteCityRequestDTO dto) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        FavoriteCity favoriteCity = new FavoriteCity(
                dto.getCity(),
                user
        );

        FavoriteCity savedCity = repository.save(favoriteCity);

        FavoriteCityEventDTO event = new FavoriteCityEventDTO(
                savedCity.getId(),
                savedCity.getCityName(),
                user.getEmail(),
                LocalDateTime.now()

        );

        rabbitMQProducer.send(event);

        return savedCity;

    }

    public List<FavoriteCity> findAllByUser() {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User favoriteOfUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return repository.findByUser(favoriteOfUser);
    }

    public void delete(Long id) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User deleteFavorite = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        FavoriteCity city = repository.findById(id)
                .orElseThrow(() ->
                        new FavoriteCityNotFoundException("Favorite city not found."));

        if (!city.getUser().getId().equals(deleteFavorite.getId())) {
            throw new FavoriteCityNotFoundException(
                    "This city does not belong to the logged user."
            );
        }

        repository.delete(city);
    }

    public List<FavoriteCityWeatherResponseDTO> getFavoriteCitiesWeather() {

        List<FavoriteCity> cities = findAllByUser();

        List<FavoriteCityWeatherResponseDTO> response =
                new ArrayList<>();

        for (FavoriteCity city : cities) {

            CurrentWeatherResponseDTO weather =
                    weatherDataService.getCurrentWeather(
                            city.getCityName()
                    );

            response.add(
                    new FavoriteCityWeatherResponseDTO(
                            weather.getCity(),
                            weather.getTemperature(),
                            weather.getHumidity(),
                            weather.getWindSpeed(),
                            weather.getAlert()
                    )
            );
        }

        return response;
    }


}


package com.zephyr.api.service;


import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.repository.FavoriteCityRepository;
import com.zephyr.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteCityService {

    private final FavoriteCityRepository repository;
    private final UserRepository userRepository;

    public FavoriteCityService(FavoriteCityRepository repository,
                               UserRepository userRepository) {
        this.repository = repository,
                this.userRepository = userRepository
    }

    public FavoriteCity save(FavoriteCity favoriteCity) {
        return repository.save(favoriteCity);
    }
}

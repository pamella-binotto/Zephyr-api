package com.zephyr.api.service;


import com.zephyr.api.dto.FavoriteCityRequestDTO;
import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.entity.User;
import com.zephyr.api.exception.FavoriteCityNotFoundException;
import com.zephyr.api.exception.UserNotFoundException;
import com.zephyr.api.repository.FavoriteCityRepository;
import com.zephyr.api.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteCityService {

    private final FavoriteCityRepository repository;
    private final UserRepository userRepository;

    public FavoriteCityService(FavoriteCityRepository repository,
                               UserRepository userRepository) {
        this.repository = repository;
                this.userRepository = userRepository;
    }

    public FavoriteCity save (FavoriteCityRequestDTO dto) {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        FavoriteCity favoriteCity = new FavoriteCity(
                dto.getCity(),
                user
        );

        return repository.save(favoriteCity);
    }

    public List<FavoriteCity> findAllByUser () {

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User favoriteOfUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        return repository.findByUser(favoriteOfUser);
    }

    public void delete (Long id){

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



}


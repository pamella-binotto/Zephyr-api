package com.zephyr.api.repository;

import com.zephyr.api.entity.FavoriteCity;
import com.zephyr.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long>
{
    List<FavoriteCity> findByUser(User user);
}

package com.zephyr.api.repository;

import com.zephyr.api.entity.FavoriteCity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCityRepository extends JpaRepository<FavoriteCity, Long>
{
}

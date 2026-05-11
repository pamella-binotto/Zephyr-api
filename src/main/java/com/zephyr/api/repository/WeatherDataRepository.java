package com.zephyr.api.repository;

import com.zephyr.api.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository <WeatherData, Long>{
}
